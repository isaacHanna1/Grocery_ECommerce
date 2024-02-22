package com.watad.Dao;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.watad.Dto.RegistrationDto;
import com.watad.model.Role;
import com.watad.model.User;
import com.watad.services.EmailValidationService;
import com.watad.services.UrlManipulator;
import com.watad.services.VerificationTokenService;


@Component
public class UserDaoImp implements UserDao{

	@Autowired
	private SessionFactory mySessionFactory;
	
	@Autowired
	private VerificationTokenService verificationTokenService;
	

    @Autowired
    private PasswordEncoder passwordEncoder;
	
	public void setMySessionFactory(SessionFactory mySessionFactory) {
		this.mySessionFactory = mySessionFactory;
	}
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private EmailValidationService emailValidationService;
	
	@Autowired
	private UrlManipulator urlManipulator;
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public void saveUser(User user) {
		Session session = this.mySessionFactory.getCurrentSession();
		session.save(user);
	}


	@Override
	@Transactional
	public void saveUser(RegistrationDto registrationDto , HttpServletRequest req) {

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
		Role role = roleDao.findByName("USER");
		user.setRoles(Arrays.asList(role));
		saveUser(user);
		
		sendEmail(user, req);
	}

	public  void sendEmail(User user , HttpServletRequest req) {
		String token = verificationTokenService
		        .generateToken();

		long expirationMilliSeconds = System.currentTimeMillis()+(60*60*1000);
		token+= "-expireIn-"+expirationMilliSeconds;
		String encodedUserId = encodeURLComponent(user.getId()+"");// Encode user ID for URL safety
		token+="?id="+encodedUserId;
		System.out.println("the token is :"+token);
		String encryptToken = urlManipulator.encrypt(token);
		System.out.println("the token after encrept :"+ encryptToken);
		emailValidationService.sendValidationEmail(user,encryptToken, req);

	}
	
	@Transactional
	@Override
	public User findByEmail(String email) {

		Session session = this.mySessionFactory.getCurrentSession();
		String Hql = "FROM users WHERE userEmail = :userEmail";
		Query  query = session.createQuery(Hql,User.class);
		query.setParameter("userEmail", email);
		@SuppressWarnings("unchecked")
		List <User> resultList = query.getResultList(); 
		
		if(!resultList.isEmpty()) {
			return resultList.get(0);	
		}
			return null ;	
	}

	@Transactional
	@Override
	public User findByPhone(String phone) {

		Session session = this.mySessionFactory.getCurrentSession();
		String Hql = "FROM users WHERE userPhone = :phone ";
		Query  query = session.createQuery(Hql,User.class);
		query.setParameter("phone", phone);
		@SuppressWarnings("unchecked")
		List <User> resultList = query.getResultList(); 
		
		if(!resultList.isEmpty()) {
			System.out.println(resultList.get(0).getCity());
			return resultList.get(0);	
		}
			return null ;	
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
		return user ; 
	}


	@Override
	@Transactional
	public void activeUserAccount(long userId) {
		System.out.println("the id is "+userId);
		User user = findById(userId);
		user.setActive(true);
		Session session = this.mySessionFactory.getCurrentSession();
		session.merge(user);
	}

}
