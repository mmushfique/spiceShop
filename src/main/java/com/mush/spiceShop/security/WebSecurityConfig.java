package com.mush.spiceShop.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Autowired
	private DataSource dataSource;

	@Bean
	public UserDetailsService adminDetailsService() {
		return new AdminServiceImpl();
	}


	//password
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


	@Bean
	public SecurityFilterChain securityChain(HttpSecurity http) throws Exception {
		http.csrf().disable().
				authorizeRequests(authorizeRequests ->
				authorizeRequests
						.antMatchers("/assets/**").permitAll()// Allow access to public resources
						.anyRequest().authenticated() // Require authentication for all other requests
		)

				.formLogin().loginPage("/login")
				.and().formLogin().loginProcessingUrl("/login")
				.and().formLogin().defaultSuccessUrl("/").permitAll()
				.and().logout().logoutSuccessUrl("/login");
		return http.build();
	}


}
