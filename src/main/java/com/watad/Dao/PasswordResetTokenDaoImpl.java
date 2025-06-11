package com.watad.Dao;


import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.watad.model.PasswordResetToken;
import org.springframework.stereotype.Repository;

@Repository
public class PasswordResetTokenDaoImpl implements PasswordResetTokenDao{

	private final SessionFactory mySessionFactory;

	public PasswordResetTokenDaoImpl(SessionFactory mySessionFactory) {
		this.mySessionFactory = mySessionFactory;
	}
	@Override
	@Transactional
	public void save(PasswordResetToken token) {
		Session session = mySessionFactory.getCurrentSession();
		session.save(token);
	}
	@Override
	@Transactional
	public PasswordResetToken findByToken(String token) {
		Session session = mySessionFactory.getCurrentSession();
		String hql      = "Select t from PasswordResetToken t where t.token =:token ";
		Query<PasswordResetToken> query     = session.createQuery(hql, PasswordResetToken.class);
		query.setParameter("token", token);
		return query.uniqueResult();
	}

	@Override
	@Transactional
	public void delete(PasswordResetToken resetToken) {
		Session session  = mySessionFactory.getCurrentSession();
		session.delete(resetToken);
	}

	
	

}
