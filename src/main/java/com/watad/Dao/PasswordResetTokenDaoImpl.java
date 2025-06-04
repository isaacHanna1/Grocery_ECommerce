package com.watad.Dao;


import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.watad.model.PasswordResetToken;

@Component
public class PasswordResetTokenDaoImpl implements PasswordResetTokenDao{

	
	@Autowired
	private SessionFactory mySessionFactory;
	
	public void setMySessionFactory(SessionFactory mySessionFactory) {
		this.mySessionFactory = mySessionFactory;
	}
	
	@Override
	@Transactional
	public void save(PasswordResetToken token) {
		Session session = mySessionFactory.getCurrentSession();
		System.out.println("im save token");
		System.out.println("the expire date is "+token.getExpireDate());
		System.out.println("the user id  "+token.getUser().getId());
		System.out.println("the token "+token.getToken());
		session.save(token);
	}

	@Override
	@Transactional
	public PasswordResetToken findByToken(String token) {

		Session session = mySessionFactory.getCurrentSession();
		String hql      = "Select t from PasswordResetToken t where t.token =:token ";
		Query<PasswordResetToken> query     = session.createQuery(hql, PasswordResetToken.class);
		query.setParameter("token", token);
		PasswordResetToken resetToken = query.uniqueResult();
		return resetToken;
	}

	@Override
	@Transactional
	public void delete(PasswordResetToken resetToken) {

		Session session  = mySessionFactory.getCurrentSession();
		session.delete(resetToken);
	}

	
	

}
