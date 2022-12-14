package br.com.alura.mvc.mudi;



import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource ds;
	
	private static final String[] openURLs = { "/home/**", "/api/**"};
	
	
	
	//Bean para encoder do passwor do HttpBasic
	/*
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
		
	}*/
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// Basic Autentication
		/*
		 * http.csrf().disable() .authorizeRequests() .anyRequest() .authenticated()
		 * .and() .httpBasic();
		 */

		// SEGURANCA Http Basic
		/*http.csrf().disable();
		http.authorizeRequests()
				.antMatchers(HttpMethod.GET, openURLs)
					.permitAll()
				.anyRequest()
					.authenticated()
				.and()
				.httpBasic()
				.and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);*/
		
		
		
		http.csrf().disable()
		.authorizeRequests()
		.antMatchers(openURLs)
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
					.username("igor")
					.password(encoder.encode("igor"))
					.roles("ADM")
					.build();*/
		
		auth.jdbcAuthentication()
			.dataSource(ds)
			.passwordEncoder(encoder);
			//.withUser(user);
		
		
		//USU??RIO AUTENTICACAO HTTP BASIC
		/*auth.inMemoryAuthentication()
		.withUser("igor").password("123456").roles("ADMIN")
		.and()
		.withUser("joao").password("1234").roles("USER");*/
	}


	
	
	//Autenticacao em mem??ria
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
