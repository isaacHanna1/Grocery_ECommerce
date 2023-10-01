package com.watad.Dao;




import java.util.List;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.watad.model.Category;


@Component
public class CategoryDaoImp implements CategoryDao{

	@Autowired
	private SessionFactory mySessionFactory;
	
	public void setMySessionFactory(SessionFactory mySessionFactory) {
		this.mySessionFactory = mySessionFactory;
	}
	
	@Override	
	public void insertNewCategory(Category category) {
	Session session = this.mySessionFactory.getCurrentSession();
	session.save(category);	
}
	
	@Override
	public Category getCategory(long id) {
		Session session = this.mySessionFactory.getCurrentSession();
		Category category = session.get(Category.class, id);
		
		if(category.getCategoryName().equals(""))
			return null ; 
		
		return category;
	}
	


	@Override
	public List<Category> getListOfCategory() {
		Session session = this.mySessionFactory.getCurrentSession();
		    CriteriaBuilder cb = session.getCriteriaBuilder();
		    CriteriaQuery<Category> cq = cb.createQuery(Category.class);
		    Root<Category> rootEntry = cq.from(Category.class);
		    CriteriaQuery<Category> all = cq.select(rootEntry);
		    TypedQuery<Category> allQuery = session.createQuery(all);
		    return allQuery.getResultList();
	}


	@Override
	public Category deleteCategory(long categoryId) {
		 Session session = this.mySessionFactory.getCurrentSession();
		 Category category = session.get(Category.class, categoryId);
		 if(category != null) {
		 session.delete(category);
		 }
		 return category;
	}

	@Override
	public Category editCategory(Category category) {
		 Session session = this.mySessionFactory.getCurrentSession();
		 session.merge(category);
		 return category;
	}

	

	
}
