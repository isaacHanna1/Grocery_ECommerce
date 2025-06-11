package com.watad.services;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;

import javax.mail.Message;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.watad.model.User;
import com.watad.notification.strategy.NotificationStrategy;


@Service
public class EmailServiceImp implements EmailService {

	
	private final NotificationStrategy notify ;

	public EmailServiceImp(NotificationStrategy notify) {
        this.notify = notify;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public boolean sendEmail(User user ,String subject ,String message  ,HttpServletRequest req) {
		try {
        notify.sendNotification(subject, user, message);
        return true;
		} catch (Exception ex) {
		    return false ; 
		}
	}





	
}
