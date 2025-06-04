package com.watad.resetPassword;

import com.watad.Dao.PasswordResetTokenDao;
import com.watad.Dao.UserDao;
import com.watad.model.PasswordResetToken;
import com.watad.model.User;

public class EmailPasswordReset  extends PaswordResetTemplate{

	
	private UserDao userDao ; 
	private TokenFactory tokenFactory;
	private NotificationStrategy notificationStrategy ; 
	private PasswordResetTokenDao passwordResetTokenDao;
	
	
	public EmailPasswordReset(UserDao userDao, TokenFactory tokenFactory,
			                  NotificationStrategy notificationStrategy , PasswordResetTokenDao passwordResetTokenDao) {
		super();
		this.userDao               = userDao;
		this.tokenFactory          = tokenFactory;
		this.notificationStrategy  = notificationStrategy;
		this.passwordResetTokenDao = passwordResetTokenDao;
	}

	@Override
	User findUser(String email) {
		return userDao.findByEmail(email);
	}

	@Override
	void handleUserNotFound() {
		throw new UserNotFoundException("This Email Not Existing!!" ,new RuntimeException());
		
	}

	@Override
	String createToken() {
		return tokenFactory.createToken();
	}

	@Override
	void saveToken(User user, String token) {
		PasswordResetToken paResetToken = new PasswordResetToken(token, user);
		passwordResetTokenDao.save(paResetToken);
	}
	
	@Override
	String generateResetUrl(String token) {
			return "http://192.168.1.3:8080/restPass?token="+token;
	}

	@Override
	void sendResetLink(String userIdentifier, String resultUrl) {
		notificationStrategy.sendResetLink(userIdentifier, resultUrl);
	}
}
