package com.watad.Dao;


import java.util.ArrayList;
import java.util.List;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.watad.Dto.SubCategoryDto;
import com.watad.model.Category;
import com.watad.model.SubCategory;
import org.springframework.stereotype.Repository;

@Repository
public class SubCategoryDaoImp implements SubCategoryDao {


	private SessionFactory mySessionFactory;
	
	private final  CategoryDao categoryDao ;

	public SubCategoryDaoImp(SessionFactory mySessionFactory, CategoryDao categoryDao) {
		this.mySessionFactory = mySessionFactory;
		this.categoryDao = categoryDao;
	}

	@Override
	public SubCategory insertNewSubCategory(SubCategory subCategory)  {
		Session session = this.mySessionFactory.getCurrentSession();
		session.save(subCategory);
		return subCategory;
	}

	@Override
	public List<SubCategoryDto> allSubCategories() {
		Session session = this.mySessionFactory.getCurrentSession();
		Query<SubCategory> fromSubCategory = session.createQuery(
				"SELECT sc FROM SubCategory sc JOIN FETCH sc.category", SubCategory.class);

		List<SubCategory> allCategories = fromSubCategory.getResultList();

		List<SubCategoryDto> subCategoryDtoList = new ArrayList<>();
		for (SubCategory subCategory : allCategories) {
			String categoryName = subCategory.getCategory().getCategoryName();
			SubCategoryDto dto = new SubCategoryDto(
					subCategory.getId(),
					subCategory.getSubCategoryName(),
					categoryName
			);
			subCategoryDtoList.add(dto);
		}
		return subCategoryDtoList;
	}

	@Override
	public void deleteSubCategory(long id ) {
		Session session = mySessionFactory.getCurrentSession();
		SubCategory subCategory = session.get(SubCategory.class, id);
		session.delete(subCategory);
	}

	@Override
	public boolean findByName(SubCategory subCategory) {
		Session session = this.mySessionFactory.getCurrentSession();
		String hql  = "FROM SubCategory C WHERE C.subCategoryName = :subCategoryName and C.category.id = :categoryId";
		Query<SubCategory> query = session.createQuery(hql,SubCategory.class);
		query.setParameter("subCategoryName", subCategory.getSubCategoryName());
		query.setParameter("categoryId", subCategory.getCategory().getId());
		List<SubCategory> results = query.list();
		if(results.size()>0) {
			return true;
		}
		return false;
	}

	@Override
	public SubCategory editSubCategory(SubCategory subCategory) {
		 Session session = this.mySessionFactory.getCurrentSession();
		 session.merge(subCategory);
		 return subCategory;
	}

	@Override
	public List<SubCategory> getSubCategoryInSuchGategory(long CategoryId) {
		Category category  = categoryDao.getCategory(CategoryId);
		category.getSubCategory().size();
		System.out.println("the size is "+category.getSubCategory());// It is better to use join => but neednot to ehnace not cost anything
		return category.getSubCategory();
	}

	@Override
	public List<SubCategory> getSubCategoryInGategory(String CategoryName) {
		Session session = this.mySessionFactory.getCurrentSession();
		String hql = "SELECT s FROM SubCategory s JOIN s.category c WHERE c.categoryName = :categoryName";
		Query<SubCategory> query = session.createQuery(hql,SubCategory.class);
		query.setParameter("categoryName", CategoryName);
		List<SubCategory> results = query.list();
		return results;
	}

	@Override
	public SubCategory findById(long id) {
		Session session = mySessionFactory.getCurrentSession();
		return  session.find(SubCategory.class,id);
	}


}

