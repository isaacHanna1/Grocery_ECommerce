package com.watad.notification.strategy;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.watad.model.User;

@Service
public class EmailNotifications implements NotificationStrategy{
	
	private final JavaMailSender mailSender;
	

    public EmailNotifications(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
	 
	@Override
	public void sendNotification(String notificationSubject, User user, String message) {
		try {
			
	        SimpleMailMessage messages = new SimpleMailMessage();
	        messages.setFrom("learntest402@gmail.com");
	        messages.setTo(user.getUserEmail());
	        messages.setSubject(notificationSubject);
	        messages.setText(message);
	        mailSender.send(messages);
			} catch (Exception ex) {
			    System.out.println("the exception happened because : "+ex.getMessage());
			    System.out.println("the exception happened because : "+ex.getCause().toString());
			}
	}

}
