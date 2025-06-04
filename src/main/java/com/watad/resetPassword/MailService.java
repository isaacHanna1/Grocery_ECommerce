package com.watad.resetPassword;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Service
public class MailService {

	private final JavaMailSender mailSender;

	@Autowired
    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
	 
    public void sendEmail(String to, String subject, String text) {
        System.out.println("Sending email to: " + to + ", subject: " + subject);

    	System.out.println("im sending mail it now " + mailSender);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
}
}