package com.watad.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogoutController {

	
	@GetMapping("/logout")
	public String logout(HttpServletRequest req , HttpServletResponse res) {
		
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if(auth != null) {
			new SecurityContextLogoutHandler().logout(req, res, auth);
			req.getSession().invalidate();
		}

		return "login";	
	} 
}
