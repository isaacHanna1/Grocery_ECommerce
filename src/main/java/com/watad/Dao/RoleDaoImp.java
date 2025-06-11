package com.watad.Dao;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.watad.model.Role;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImp implements RoleDao {



	private final SessionFactory mySessionFactory;

	public RoleDaoImp(SessionFactory mySessionFactory) {
		this.mySessionFactory = mySessionFactory;
	}

	@Override
	public Role findByName(String roleName) {
	  Session session = this.mySessionFactory.getCurrentSession();
	  String hql = "FROM roles WHERE roleName = :roleName";
		Query<Role> query = session.createQuery(hql);
	    query.setParameter("roleName", roleName);
        return query.uniqueResult();
	}

}
