package com.watad.notification.strategy;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.watad.model.User;

import javax.mail.internet.MimeMessage;

@Service
public class EmailNotifications implements NotificationStrategy{
	
	private final JavaMailSender mailSender;
	

    public EmailNotifications(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

	@Override
	public void sendNotification(String notificationSubject, User user, String message) {
		try {
			MimeMessage mimeMessage    = mailSender.createMimeMessage();
			MimeMessageHelper helper   = new MimeMessageHelper(mimeMessage,true);
			helper.setFrom("learntest402@gmail.com");
			helper.setTo(user.getUserEmail());
			helper.setSubject(notificationSubject);
			helper.setText(message, true);
			mailSender.send(mimeMessage);
			System.out.println("Email sent successfully!");
			System.out.println("Sending email to: " + user.getUserEmail());
			System.out.println("Message Subject: " + notificationSubject);
		} catch (Exception ex) {
			    System.out.println("the exception happened because : "+ex.getMessage());
			    System.out.println("the exception happened because : "+ex.getCause().toString());
			}
	}

}
