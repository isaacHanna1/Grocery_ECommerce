package com.watad.Dao;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.watad.model.Role;

@Component
public class RoleDaoImp implements RoleDao {


	@Autowired
	private SessionFactory mySessionFactory;
	
	
	@Override
	public Role findByName(String roleName) {
	  Session session = this.mySessionFactory.getCurrentSession();
	  String hql = "FROM roles WHERE roleName = :roleName";
	    @SuppressWarnings("unchecked")
		Query<Role> query = session.createQuery(hql);
	    query.setParameter("roleName", roleName);
        return query.uniqueResult();
	}

}
