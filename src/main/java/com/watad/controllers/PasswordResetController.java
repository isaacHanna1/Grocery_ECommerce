package com.watad.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.watad.Dao.PasswordResetTokenDao;
import com.watad.Dao.UserDao;
import com.watad.model.PasswordResetToken;
import com.watad.resetPassword.EmailPasswordReset;
import com.watad.resetPassword.NotificationStrategy;
import com.watad.resetPassword.PaswordResetTemplate;
import com.watad.resetPassword.TokenFactory;
import com.watad.resetPassword.UUIDTokenFactory;

@RestController
public class PasswordResetController {

	@Autowired
	private UserDao userDao;
	@Autowired
	private PasswordResetTokenDao passwordResetTokenDao;
	@Autowired
	private NotificationStrategy emailStrategy  ;
	
	private TokenFactory tokenFactory           = new UUIDTokenFactory();
    
	
	@PostMapping(path = "/forget")
	public String processForgetPasswordForm(@RequestParam("email") String email ,Model model ) {
		System.out.println("im called y man");
		PaswordResetTemplate paswordResetTemplate =
		new EmailPasswordReset(userDao,tokenFactory,emailStrategy,passwordResetTokenDao);
		paswordResetTemplate.resetPassword(email);
		model.addAttribute("restMesage" , "A Password reset link has been sent to your email");
		return "forget-password";
	}
	  @GetMapping("/restPass")
	    public ModelAndView resetPassword(@RequestParam("token") String token) {
	        System.out.println("Received token: " + token);

	        ModelAndView modelAndView = new ModelAndView();

	        PasswordResetToken passToken = passwordResetTokenDao.findByToken(token);
	        if (passToken != null && !passToken.isExpired()) {
	        	modelAndView.addObject("username", passToken.getUser().getUserEmail());
	            modelAndView.setViewName("resetPassword");  
	        } else {
	            modelAndView.setViewName("errorPage"); 
	            modelAndView.addObject("errorMessage", "انتهت صلاحية الطلب , اعد المحاولة مرة أخرى");
	        }

	        return modelAndView;
	    }
	  
	  @PostMapping("/updatePassword")
	  public ModelAndView updatePassword(@RequestParam String userName,
	                               @RequestParam String newPassword) {
		  ModelAndView modelAndView = new ModelAndView();
		  System.out.println("im in update password ->");
		  userDao.updatePassword(userName, newPassword);
		  modelAndView.setViewName("login");
		  return modelAndView;
	  }
}
