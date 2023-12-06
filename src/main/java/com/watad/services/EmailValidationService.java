package com.watad.services;


import javax.servlet.http.HttpServletRequest;

import com.watad.model.User;
public interface EmailValidationService {

	void sendValidationEmail(User user, HttpServletRequest req);
	
	void activateAccount(String token);
	
	String getDomain(HttpServletRequest request);
	
}
