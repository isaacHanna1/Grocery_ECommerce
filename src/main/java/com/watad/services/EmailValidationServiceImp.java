package com.watad.services;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.watad.Dao.UserDao;
import com.watad.Dao.VerificationTokenDao;
import com.watad.model.User;
import com.watad.model.VerificationToken;

@Service
public class EmailValidationServiceImp implements EmailValidationService {

	 @Autowired
	 private JavaMailSender mailSender;
	 
	
	 @Autowired
	 private VerificationTokenDao verificationTokenDao;

	 @Autowired
	 private SessionFactory mySessionFactory;
		
	
	@Override
	public void sendValidationEmail(User user , HttpServletRequest req) {
		
		String token = verificationTokenDao.
						getVerificationToken(user.getId());
		
		String activationLink = getDomain(req) +"/active/"+token ;
		
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getUserEmail());
        mailMessage.setSubject("Activate Your Account");
        mailMessage.setText("To activate your account, click here: " + activationLink);
        mailSender.send(mailMessage);
		
	}

	@Override
	@Transactional
	public void activateAccount(String token) {
		Session session = this.mySessionFactory.getCurrentSession();
		User user = verificationTokenDao.getUser(token);
		user.setActive(true);
		session.update(user);
		
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
	
	
	
}
