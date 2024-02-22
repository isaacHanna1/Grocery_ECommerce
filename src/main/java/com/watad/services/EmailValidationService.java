package com.watad.services;


import javax.servlet.http.HttpServletRequest;

import com.watad.model.User;
public interface EmailValidationService {

	boolean sendValidationEmail(User user,String token , HttpServletRequest req);
	String getDomain(HttpServletRequest request);
	
}
