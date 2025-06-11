package com.watad.services;


import javax.servlet.http.HttpServletRequest;

import com.watad.model.User;
public interface EmailService {

	boolean sendEmail(User user,String subject,String Message , HttpServletRequest req);
}
