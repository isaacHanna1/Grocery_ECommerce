package com.watad.services;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Service;

import com.watad.customException.InactiveUserException;
import org.springframework.security.authentication.BadCredentialsException;
import  org.springframework.security.authentication.InternalAuthenticationServiceException;
@Service
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		// if error true = not found user 
        String targetUrl = "/login?error";
        Class<? extends AuthenticationException> exceptionType = exception.getClass();
        System.out.println("Exception Type: " + exceptionType.getName());
        if(exception instanceof BadCredentialsException) {
          	System.out.println("customauth failure");
        	targetUrl += "&errorType=notfound";
        	System.out.println("not found exception  - " + targetUrl);
        }
        if (exception instanceof InternalAuthenticationServiceException) {
            Throwable internalCause = exception.getCause();

          if(internalCause instanceof InactiveUserException) {
        	  	targetUrl += "&errorType=inactive";
        		System.out.println("Inactive exception  - " + targetUrl);
        	}
        }
       response.sendRedirect(request.getContextPath() + targetUrl);

	}

}
