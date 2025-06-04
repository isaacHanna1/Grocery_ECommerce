package com.watad.resetPassword;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailNotifcationStrategy implements NotificationStrategy {

	
	private MailService mailService;
	
	@Autowired
	public EmailNotifcationStrategy(MailService mailService) {
	        this.mailService = mailService;
	}

	@Override
	public void sendResetLink(String destination, String resetUrl) {
	    if (mailService == null) {
        } else {
            String subject = "Password Reset Request";
            String message = "To reset your password, click the link below:\n" + resetUrl;
            mailService.sendEmail(destination, subject, message);
        }
    }	
}
