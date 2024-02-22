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
import com.watad.services.CustomAuthenticationFailureHandler;
import com.watad.services.CustomUserDetailsService;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration 
extends WebSecurityConfigurerAdapter{
	
	
	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Autowired
	private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
	
	
	@Override
	protected void configure(HttpSecurity http ) throws Exception {
		 http
         .csrf().disable() // Disable CSRF protection
         .authorizeRequests()
         .antMatchers("https://fonts.googleapis.com/**").permitAll()
         .antMatchers("/resources/**").permitAll()
         .antMatchers("/marketPlace").permitAll()
         .antMatchers("/subCategory/**").permitAll()
         .antMatchers("/items/**").permitAll()
         .antMatchers("/itemsInSuchCategoryAndSubCategory/**").permitAll()
         .antMatchers("/active/**").permitAll()
             .antMatchers("/signUp", "/add-user","/login", "/errorPage").permitAll()
             .antMatchers("/categoryPage","/itemPage","/allItems/**").hasRole("ADMIN")
             .anyRequest().authenticated()  // Require authentication for any other request
             .and()
         .formLogin()
             .loginPage("/login")
             .defaultSuccessUrl("/marketPlace")
             .failureHandler(customAuthenticationFailureHandler)
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
			// Add logging
		    authenticationProvider.setPostAuthenticationChecks(userDetails -> {
		        System.out.println("Authentication success for: " + userDetails.getUsername());
		    });
			return authenticationProvider;
		}
		

		  @Override
		    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			  auth.authenticationProvider(authProvider());
		  }
		
}
