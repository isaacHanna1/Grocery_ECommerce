package com.watad.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.watad.Dao.UserDao;
import com.watad.model.User;

@ControllerAdvice
public class GlobalControllerUserLogin {
	
	
	@Autowired 
	private UserDao userDao;
	
    @ModelAttribute
    public void addAttributes(Model model, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
        	String userName = authentication.getName();
        	User user = userDao.findByEmail(userName);
        	model.addAttribute("userData",user);
        }
    }

}
