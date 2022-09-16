package br.com.alura.mvc.mudi;



import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource ds;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// Basic Autentication
		/*
		 * http.csrf().disable() .authorizeRequests() .anyRequest() .authenticated()
		 * .and() .httpBasic();
		 */

		// Login Autenticatio
		http
		.authorizeRequests()
			.anyRequest().authenticated()
			.and()
			.formLogin(form -> form
				.loginPage("/login")
				.defaultSuccessUrl("/usuario/pedidos", true)
				.permitAll()
			)
			.logout(logout -> logout.logoutUrl("/logout"))
			.csrf().disable();

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		
		/*UserDetails user = 
				User.builder()
					.username("maria")
					.password(encoder.encode("maria"))
					.roles("ADM")
					.build();*/
		
		auth.jdbcAuthentication()
			.dataSource(ds)
			.passwordEncoder(encoder);
			//.withUser(user);
	}


	
	
	//Autenticacao em memória
	/*@Bean
	 * @Override
	public UserDetailsService userDetailsService() {
		UserDetails user = 
				User.withDefaultPasswordEncoder()
					.username("joao")
					.password("joao")
					.roles("ADM")
					.build();

		return new InMemoryUserDetailsManager(user);
	}*/

}
