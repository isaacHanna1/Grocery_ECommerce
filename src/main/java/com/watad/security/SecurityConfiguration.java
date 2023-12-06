package com.watad.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import com.watad.services.CustomUserDetailsService;
@Configuration
@EnableWebSecurity
public class SecurityConfiguration 
extends WebSecurityConfigurerAdapter{
	
	
	@Autowired
	private CustomUserDetailsService userDetailsService;

  
	
	
	@Override
	protected void configure(HttpSecurity http ) throws Exception {
		 http
         .csrf().disable() // Disable CSRF protection
         .authorizeRequests()
         .antMatchers("https://fonts.googleapis.com/**").permitAll()
         .antMatchers("/resources/**").permitAll()
         .antMatchers("/marketPlace").permitAll()
             .antMatchers("/signUp", "/add-user","/login", "/errorPage").permitAll()             
             .anyRequest().authenticated()  // Require authentication for any other request
             .and()
         .formLogin()
             .loginPage("/login")
             .defaultSuccessUrl("/marketPlace")
             .permitAll()
             .and()
         .logout()
             .permitAll();
	} 
	

		@Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
		
		@Bean
		public DaoAuthenticationProvider authProvider() {
			DaoAuthenticationProvider authenticationProvider 
			= new DaoAuthenticationProvider();
			authenticationProvider.setPasswordEncoder(passwordEncoder());
			authenticationProvider.setUserDetailsService(userDetailsService);
			return authenticationProvider;
		}
		

		  @Override
		    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			  auth.authenticationProvider(authProvider());
		  }
		
}
