package com.watad.services;

import javax.servlet.http.HttpServletRequest;
import com.watad.Dto.RegistrationDto;
import com.watad.utils.Utils;
import org.springframework.stereotype.Service;
import com.watad.Dao.UserDao;
import com.watad.model.User;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class UserServiceImp implements UserService{


	private final  UserDao userDao;
	private final EmailService emailService;
	private final VerificationTokenService verificationTokenService;
	private final UrlManipulatorService urlManipulatorService;

	public UserServiceImp(UserDao userDao, EmailService emailService, VerificationTokenService verificationTokenService, UrlManipulatorService urlManipulatorService) {
		this.userDao = userDao;
		this.emailService = emailService;
		this.verificationTokenService = verificationTokenService;
		this.urlManipulatorService = urlManipulatorService;
	}

	@Transactional
	public void changeUserAddress(long userId,String goverment , String city , String userAdress) {
		userDao.updateUserAddress(userId, goverment, city, userAdress);
	}
	@Transactional
	public String getUserRole(long id) {
		User user = userDao.findById(id); 
		if (user == null )return "USER"; // this normal user
		
		return user.getRoles().get(0).getRoleName();
	}
	@Transactional
	public String getEmailByPhone(String phone) {
		return userDao.findEmailByPhone(phone);
	}

	@Override
	@Transactional
	public void saveUser(User user) {
		userDao.saveUser(user);
	}

	@Override
	@Transactional
	public void saveUser(RegistrationDto registrationDto, HttpServletRequest req) {
		User user =  userDao.saveUser(registrationDto,req);
		String message = prepareMessageForActiviation(user,req);
		emailService.sendEmail(user,"Active Account ",message, req);
	}

	@Override
	@Transactional(readOnly = true)
	public User findByEmail(String email) {
		return  userDao.findByEmail(email);
	}

	@Override
	@Transactional(readOnly = true)
	public User findByPhone(String phone) {
		return  userDao.findByPhone(phone);
	}

	@Override
	@Transactional(readOnly = true)
	public User findById(long id) {
		return  userDao.findById(id);
	}

	@Override
	@Transactional
	public void activeUserAccount(long id) {
		userDao.activeUserAccount(id);

	}

	@Override
	@Transactional
	public void updateUserAddress(long userId, String governement, String city, String userAddress) {
			userDao.updateUserAddress(userId,governement,city,userAddress);
	}

	@Override
	@Transactional
	public void updatePassword(String userName, String newPassword) {
		userDao.updatePassword(userName,newPassword);
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> getAllUser() {
		return  userDao.getAllUser();
	}


	private String  prepareMessageForActiviation(User user , HttpServletRequest req){
		String token =  verificationTokenService.generateToken();
		long expirationMilliSeconds = System.currentTimeMillis()+(60*60*1000);
		token+= "-expireIn-"+expirationMilliSeconds;
		String encodedUserId = Utils.encodeURL(user.getId()+"");
		token+="?id="+encodedUserId;
		String encryptToken = urlManipulatorService.encrypt(token);
		encryptToken        = Utils.getDomain(req)+"/active/"+encryptToken;
		return  encryptToken;

	}
}
