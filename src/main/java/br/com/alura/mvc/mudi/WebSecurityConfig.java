package br.com.alura.mvc.mudi;



import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource ds;
	
	private static final String[] openURLs = { "/home/**", "/api/**"};
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// Basic Autentication
		/*
		 * http.csrf().disable() .authorizeRequests() .anyRequest() .authenticated()
		 * .and() .httpBasic();
		 */

		// HttpAutentication
		//http.authorizeRequests().antMatchers(HttpMethod.GET, openURLs).permitAll();
		/*http.csrf().disable();
		http.authorizeRequests().antMatchers(openURLs)
				.fullyAuthenticated().and().httpBasic()
				.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);*/
		
		http.csrf().disable()
		.authorizeRequests()
		.antMatchers(HttpMethod.GET, openURLs)
		.permitAll()
		.anyRequest()
			.authenticated()
		.and()
		.formLogin(form -> form
			.loginPage("/login")
			.defaultSuccessUrl("/usuario/pedidos", true)
			.permitAll()
		)
		.logout(logout -> {
			logout.logoutUrl("/logout")
				.logoutSuccessUrl("/home");
		});
			

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
		
		
		//USUÁRIO AUTENTICACAO HTTP BASIC
		/*auth.inMemoryAuthentication()
		.withUser("igor").password("123456").roles("ADMIN")
		.and()
		.withUser("joao").password("1234").roles("USER");*/
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
