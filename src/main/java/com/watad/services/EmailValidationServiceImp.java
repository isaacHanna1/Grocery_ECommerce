package com.watad.services;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.watad.model.User;


@Service
public class EmailValidationServiceImp implements EmailValidationService {

	
	private final JavaMailSender mailSender;

    public EmailValidationServiceImp(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
	 
	
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public boolean sendValidationEmail(User user ,String token  ,HttpServletRequest req) {
		
		try {
		String activationLink = getDomain(req) +"/active/"+token ;	   
        SimpleMailMessage messages = new SimpleMailMessage();
        messages.setFrom("learntest402@gmail.com");
        messages.setTo(user.getUserEmail());
        messages.setSubject("Activate Your Account");
        messages.setText("To activate your account, click here: " + activationLink);
        System.out.println("all things runs well till now");
        
        mailSender.send(messages);
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
