package com.watad.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
@Configuration
@EnableWebSecurity
public class SecurityConfiguration 
extends WebSecurityConfigurerAdapter{
	
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	    @Bean
	    @Override
	    public UserDetailsService userDetailsService() {
	        UserDetails user = User
	            .withUsername("isaac")
	            .password(passwordEncoder().encode("12"))
	            .roles("USER")
	            .build();

	        return new InMemoryUserDetailsManager(user);
	    }
	
	
	@Override
	protected void configure(HttpSecurity http ) throws Exception {
		http
			 .authorizeRequests()
			 .antMatchers("/login","/marketPlace","/signUp").permitAll()
			 .antMatchers("/*").access("hasRole('USER')")
			 .and().formLogin()
			 .loginPage("/login")
             .permitAll()
             .and()
         .logout()
             .permitAll();
	} 
}
