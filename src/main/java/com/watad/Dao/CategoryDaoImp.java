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
import org.springframework.stereotype.Repository;


@Repository
public class CategoryDaoImp implements CategoryDao{


	private final SessionFactory mySessionFactory;


	public CategoryDaoImp(SessionFactory mySessionFactory) {
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
		return session.get(Category.class, id);
	}
	


	@Override
	public List<Category> getListOfCategory() {
		Session session = mySessionFactory.getCurrentSession();
		return session.createQuery("FROM Category", Category.class).getResultList();
	}


	@Override
	public Category deleteCategory(long categoryId) {
		Session session = mySessionFactory.getCurrentSession();
		Category category = session.get(Category.class, categoryId);
		session.delete(category); // Safe to call even if category is null
		return category;
	}

	@Override
	public Category editCategory(Category category) {
		 Session session = this.mySessionFactory.getCurrentSession();
		 session.merge(category);
		 return category;
	}

	

	
}
