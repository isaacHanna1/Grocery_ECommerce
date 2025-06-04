package com.watad.services;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.watad.model.User;
import com.watad.notification.strategy.NotificationStrategy;
import com.watad.resetPassword.EmailNotifcationStrategy;
import com.watad.notification.strategy.EmailNotifications;


@Service
public class EmailValidationServiceImp implements EmailValidationService {

	
	private final NotificationStrategy notify ;
	
	public EmailValidationServiceImp (NotificationStrategy notify) {
        this.notify = notify;

	}
	

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public boolean sendValidationEmail(User user ,String token  ,HttpServletRequest req) {
		
		try {
		String activationLink = getDomain(req) +"/active/"+token ;	   
        String subject 		  = "Activate Your Account";
        String message   	  = "To activate your account "+user.getUserName()+" , click here: " + activationLink;
        notify.sendNotification(subject, user, message);
        return true;
		} catch (Exception ex) {
		    System.out.println("the exception happened because : "+ex.getMessage());
		    System.out.println("the exception happened because : "+ex.getCause().toString());
		    return false ; 
		}
	}


	@Override
	public String getDomain(HttpServletRequest request) {
		 try {
		        URI uri = new URI(request.getRequestURL().toString());
		        String domainWithPort = uri.getHost() + ":" + uri.getPort();
		        return (domainWithPort.startsWith("www.")) ? domainWithPort.substring(4) : domainWithPort;
		    } catch (URISyntaxException e) {
		        return null;
		    }
	}
	
	
	 public static String encodeURL(String url) {
	        try {
	            // Encode the URL using UTF-8 encoding
	            return URLEncoder.encode(url, "UTF-8");
	        } catch (UnsupportedEncodingException e) {
	            // Handle encoding exception
	            e.printStackTrace();
	            return null;
	        }
	    }
	
	
}
