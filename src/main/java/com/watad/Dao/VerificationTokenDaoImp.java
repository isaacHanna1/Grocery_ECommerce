package com.watad.Dao;

import com.watad.Dto.verificationTokenDto;
import com.watad.model.User;
import com.watad.model.VerificationToken;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VerificationTokenDaoImp implements VerificationTokenDao {

	@Autowired
	private SessionFactory mySessionFactory;
	
	@Transactional
	@Override
	public void createVerificationToken(verificationTokenDto tokenDto) {
		Session session = this.mySessionFactory.getCurrentSession();
		VerificationToken token = new VerificationToken();
		token.setToken(tokenDto.getToken());
		token.setExpireDate(tokenDto.getExpireDate());
		token.setUser(tokenDto.getUser());
		session.save(token);
	}

	@Override
	@Transactional
	public User getUser(String token) {

		Session session = this.mySessionFactory.getCurrentSession();
		
		String Hql = "From VerificationToken WHERE token =:token";
		Query<VerificationToken> query = session.createQuery(Hql, VerificationToken.class);
		query.setParameter("token", token);
		return query.getResultList().get(0).getUser();
	}

	@Override
	@Transactional
	public String getVerificationToken(long userId) {
		
		Session session = this.mySessionFactory.getCurrentSession();
		String Hql = "SELECT  vt.token From VerificationToken vt WHERE vt.user.id = :userId";
		Query<String> query = session.createQuery(Hql, String.class);
		query.setParameter("userId", userId);
		return query.getResultList().get(0);
	}

	@Override
	@Transactional
	public VerificationToken getVerificationTokenBytoken(String token) {
		Session session = this.mySessionFactory.getCurrentSession();
		String Hql = "From VerificationToken vt WHERE vt.token =:token";
		Query<VerificationToken> query = session.createQuery(Hql, VerificationToken.class);
		query.setParameter("token", token);
		return query.getResultList().get(0);
	}
	
	

}
