package com.watad.Dao;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;
import com.watad.Dto.RegistrationDto;
import com.watad.Dto.verificationTokenDto;
import com.watad.model.Role;
import com.watad.model.User;
import com.watad.services.EmailValidationService;
import com.watad.services.VerificationTokenService;

@Component
public class UserDaoImp implements UserDao{

	@Autowired
	private SessionFactory mySessionFactory;
	
	@Autowired
	private VerificationTokenService verificationTokenService;
	
	@Autowired
	private VerificationTokenDao verificationTokenDao;
	
	

    @Autowired
    private PasswordEncoder passwordEncoder;
	
	public void setMySessionFactory(SessionFactory mySessionFactory) {
		this.mySessionFactory = mySessionFactory;
	}
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private EmailValidationService emailValidationService;
	
	@Override
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
		
		String token = verificationTokenService
				        .generateToken();
		LocalDateTime expireDate = verificationTokenService
				        .expireDate();
		verificationTokenDto verTokenDto = 
				new verificationTokenDto();
		verTokenDto.setToken(token);
		verTokenDto.setExpireDate(expireDate);
		verTokenDto.setUser(user);
		
		verificationTokenDao.createVerificationToken(verTokenDto);
		emailValidationService.sendValidationEmail(user, req);
	}


	@Transactional
	@Override
	public User findByEmail(String email) {

		Session session = this.mySessionFactory.getCurrentSession();
		String Hql = "FROM users WHERE userEmail = :userEmail ";
		Query  query = session.createQuery(Hql,User.class);
		query.setParameter("userEmail", email);
		List <User> resultList = query.getResultList(); 
		
		if(!resultList.isEmpty()) {
			System.out.println(resultList.get(0).getCity());
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
		List <User> resultList = query.getResultList(); 
		
		if(!resultList.isEmpty()) {
			System.out.println(resultList.get(0).getCity());
			return resultList.get(0);	
		}
			return null ;	
	}

}
