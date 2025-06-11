package com.watad.Dao;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.watad.model.Unit;
import org.springframework.stereotype.Repository;

@Repository
public class UnitDaoImp implements UnitDao{


	private final  SessionFactory mySessionFactory;

	public UnitDaoImp(SessionFactory mySessionFactory) {
		this.mySessionFactory = mySessionFactory;
	}

	@Override
	public Unit addUnit(Unit unit) {	
		Session session = this.mySessionFactory.getCurrentSession();
		session.save(unit);
		return unit;
	}


	@Override
	public List<Unit> getAllUnit() {
		Session session = this.mySessionFactory.getCurrentSession();
		return session.createQuery("From Unit ",Unit.class).getResultList();
	}


	@Override
	public Unit DeleteUnit(long id) {
		 Session session = this.mySessionFactory.getCurrentSession();
		 Unit unit = session.get(Unit.class, id);
		 if(unit != null) {
		 session.delete(unit);
		 return unit;
		 }
		 return null;
	}
	
	@Override
	public Unit EditUnit(Unit unit) {
		Session session = this.mySessionFactory.getCurrentSession();
		 session.merge(unit);
		 return unit;
	}
	@Override
	public Unit findByName(String unitName) {
		Session session 		= this.mySessionFactory.getCurrentSession();
		String hql 				= "FROM Unit U WHERE U.unitName = :unitName";
		Query<Unit> query 		= session.createQuery(hql, Unit.class);
		query.setParameter("unitName", unitName);
		return  query.getSingleResult();
	}

	@Override
	public Unit findById(long id) {
		Session session 		= mySessionFactory.getCurrentSession();
		return  session.find(Unit.class,id);
	}
}
