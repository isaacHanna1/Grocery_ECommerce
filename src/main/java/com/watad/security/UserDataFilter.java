package com.watad.security;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import com.watad.model.User;
import java.io.IOException;

@WebFilter("/*")
@Component
public class UserDataFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // No initialization needed in this example
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // Cast to HttpServletRequest for convenience
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        // Get the authentication object
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        System.out.println("Im here ");
        // Check if authentication is valid
        if (authentication != null && authentication.isAuthenticated() 
        		&& !(authentication instanceof AnonymousAuthenticationToken)) {
        }else {
        	User user = createDefaultUser();
        	httpRequest.setAttribute("userData", user);
        }
        // Continue with the filter chain
        chain.doFilter(request, response);
    }

    private User createDefaultUser() {
        // Customize with your default user values
    	User user = new User();
    	user.setUserName("زائر");
    	user.setGovernment("مصر");
    	user.setCity("القاهرة");
    	user.setId(0);
        return user;
    }

    @Override
    public void destroy() {
        // No specific cleanup needed here
    }
}