package com.watad.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
        	User user = userDao.findByPhone(userName);
        	model.addAttribute("userData",user);
            model.addAttribute("loggedIn", true);
        }else {
        	User user = createDefaultUser();
        	model.addAttribute("userData",user);
        	model.addAttribute("loggedIn", false);
        }
        
        
        
    }
    
    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception ex) {
        ModelAndView mav = new ModelAndView("errorPage");
        mav.addObject("errorMessage", ex.getMessage());
        // Log the exception here
        return mav;
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
}
