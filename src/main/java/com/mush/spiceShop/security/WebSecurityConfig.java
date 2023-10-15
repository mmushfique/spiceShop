package com.mush.spiceShop.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

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
	public DaoAuthenticationProvider adminauthenticationProvider() {
		DaoAuthenticationProvider authProvider =new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(adminDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}


//	@Autowired
//    public void configAuthentication(AuthenticationManagerBuilder authAdmin) throws Exception {
//
//
//		authAdmin
//        	.authenticationProvider(adminauthenticationProvider())
//        	.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder())
//            .dataSource(dataSource)
//            .usersByUsernameQuery("select username, password, status from admin where username=?")
//            .authoritiesByUsernameQuery("select username, role from admin where username=?")
//            ;
//
//    }
//


    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http
       .csrf().disable()

        .authorizeRequests()

        .antMatchers("/assets/**").permitAll()

        .antMatchers("/**").hasAuthority("ADMIN")
			   .anyRequest().authenticated()



        .and()

            .formLogin()
//               .loginPage("/signin.html").permitAll()
            .and()
           .logout()
			   .logoutUrl("/logout") // URL for the logout request
			   .logoutSuccessUrl("/login") // Redirect after logout
			   .invalidateHttpSession(true) .permitAll()
           .and()
           .rememberMe()
           .and()
          .exceptionHandling().accessDeniedPage("/403");

    }
    //.usernameParameter("username").passwordParameter("password")
    //.loginPage("login")
    //.permitAll()


}
