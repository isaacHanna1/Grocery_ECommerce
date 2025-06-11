package com.watad.Dao;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import com.watad.services.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.watad.Dto.RegistrationDto;
import com.watad.model.Role;
import com.watad.model.User;


@Repository
@Transactional
public class UserDaoImp implements UserDao{

	private  Logger logger = LogManager.getLogger(UserDaoImp.class);


	private  final  SessionFactory mySessionFactory;

	private  PasswordEncoder passwordEncoder;
	private final RoleService roleService;


	public UserDaoImp(SessionFactory mySessionFactory, VerificationTokenService verificationTokenService, RoleService roleService, EmailService emailValidationService, UrlManipulatorService urlManipulatorService) {
		this.mySessionFactory = mySessionFactory;
		this.roleService = roleService;
	}

	@Autowired
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public void saveUser(User user) {
		Session session = this.mySessionFactory.getCurrentSession();
		session.save(user);
	}


	@Override
	public User saveUser(RegistrationDto registrationDto , HttpServletRequest req) {

		User user = new User();
		user.setUserName(registrationDto.getUserName());
		user.setGovernment(registrationDto.getGovernment());
		user.setCity(registrationDto.getCity());
		user.setUserPhone(registrationDto.getPhone());
		user.setUserEmail(registrationDto.getEmail());
		
		//encoded password
		String password = registrationDto.getPassword();
		String passwordEncoded = passwordEncoder.encode(password);
		user.setPassword(passwordEncoded);
		user.setUserAddress(registrationDto.getAddress());
		Role role = roleService.findByName("USER");
		user.setRoles(Arrays.asList(role));
		saveUser(user);
		return user;
	}


	
	@Override
	public User findByEmail(String email) {

		Session session = this.mySessionFactory.getCurrentSession();
		String Hql = "FROM users WHERE userEmail = :userEmail";
		Query  query = session.createQuery(Hql,User.class);
		query.setParameter("userEmail", email);
		@SuppressWarnings("unchecked")
		List <User> resultList = query.getResultList();
		if (resultList.isEmpty())
			return null;
		return  resultList.get(0);
	}

	@Override
	public User findByPhone(String phone) {

		Session session = this.mySessionFactory.getCurrentSession();
		String Hql = "FROM users WHERE userPhone = :phone ";
		Query  query = session.createQuery(Hql,User.class);
		query.setParameter("phone", phone);
		@SuppressWarnings("unchecked")
		List <User> resultList = query.getResultList();
		if ((resultList.isEmpty()))
			return null;

		return  resultList.get(0);

	}

	@Override
	public String findEmailByPhone(String phone) {
		Session session = this.mySessionFactory.getCurrentSession();
		String hql = "SELECT u.userEmail FROM users u WHERE u.userPhone = :phone";
		Query query = session.createQuery(hql);
		query.setParameter("phone", phone);

	    String email = (String) query.getSingleResult();
	    try {
	        return email;
	    }  catch (Exception e) {
	        e.printStackTrace();
	    }
	    return email;
	} 

	 public static String encodeURLComponent(String component) {
	        try {
	            return new URI(null, null, component, null).toASCIIString();
	        } catch (URISyntaxException e) {
	            throw new IllegalArgumentException("Invalid URL component: " + component, e);
	        }
	    }

	public User findById(long id) {
		Session session = this.mySessionFactory.getCurrentSession();
		User user = session.get(User.class,id);
		if(user == null)
			return  null ;
		return user ; 
	}


	@Override
	public void activeUserAccount(long userId) {
		User user = findById(userId);
		user.setActive(true);
		Session session = this.mySessionFactory.getCurrentSession();
		session.merge(user);
	}

	@Override
	public void updateUserAddress(long userId, String goverment, String city, String userAddress) {
		User user = findById(userId);
		user.setGovernment(goverment);
		user.setCity(city);
		user.setUserAddress(userAddress);
		Session session = mySessionFactory.getCurrentSession();
		session.merge(user);
	}


	@Override
	public void updatePassword(String userName, String newPassword) {
		Session session = mySessionFactory.getCurrentSession();
		User user = findByEmail(userName);
		if(user != null) {
			String hashedPassword = passwordEncoder.encode(newPassword);
			user.setPassword(hashedPassword);
			session.update(user);
		}
	}
	public List<User> getAllUser(){
		Session session = mySessionFactory.getCurrentSession();
		return session.createQuery("FROM users ",User.class).getResultList();
	}
	
}
