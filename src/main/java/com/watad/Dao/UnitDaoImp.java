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

@Component
public class UnitDaoImp implements UnitDao{

	@Autowired
	private SessionFactory mySessionFactory;
	
	public void setMySessionFactory(SessionFactory mySessionFactory) {
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
	    CriteriaBuilder cb = session.getCriteriaBuilder();
	    CriteriaQuery<Unit> cq = cb.createQuery(Unit.class);
	    Root<Unit> rootEntry = cq.from(Unit.class);
	    CriteriaQuery<Unit> all = cq.select(rootEntry);
	    TypedQuery<Unit> allQuery = session.createQuery(all);
	    return allQuery.getResultList();
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
	public boolean findByName(String unitName) {
		Session session = this.mySessionFactory.getCurrentSession();
		String hql  = "FROM Unit U WHERE U.unitName = :unitName";
		Query<Unit> query = session.createQuery(hql,Unit.class);
		query.setParameter("unitName", unitName);
		List<Unit> results = query.list();
		if(results.size()>0) {
			return true;
		}
		return false;
	}
}
