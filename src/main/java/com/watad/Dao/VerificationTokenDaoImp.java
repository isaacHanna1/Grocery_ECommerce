//package com.watad.Dao;
//
//import com.watad.Dto.verificationTokenDto;
//import com.watad.model.VerificationToken;
//
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//@Component
//public class VerificationTokenDaoImp implements VerificationTokenDao {
//
//	@Autowired
//	private SessionFactory mySessionFactory;
//	
//	
//	@Override
//	@Transactional(propagation = Propagation.SUPPORTS)
//	public void createVerificationToken(verificationTokenDto tokenDto) {
//		Session session = this.mySessionFactory.getCurrentSession();
//		VerificationToken token = new VerificationToken();
//		token.setToken(tokenDto.getToken());
//		token.setExpireDate(tokenDto.getExpireDate());
//		token.setUser(tokenDto.getUser());
//		session.save(token);
//	}
//
//	
//
//
//	
//
//}
