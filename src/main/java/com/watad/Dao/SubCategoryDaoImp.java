package com.watad.Dao;


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

@Component
public class SubCategoryDaoImp implements SubCategoryDao {


	@Autowired
	private SessionFactory mySessionFactory;
	
	public void setMySessionFactory(SessionFactory mySessionFactory) {
		this.mySessionFactory = mySessionFactory;
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
		CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<SubCategoryDto> criteriaQuery = builder.createQuery(SubCategoryDto.class);

        Root<SubCategory> subCategoryRoot = criteriaQuery.from(SubCategory.class);
        Join<SubCategory, Category> categoryJoin = subCategoryRoot.join("category", JoinType.INNER);

        criteriaQuery.select(builder.construct(
                SubCategoryDto.class,
                subCategoryRoot.get("id"),
                subCategoryRoot.get("subCategoryName"),
                categoryJoin.get("categoryName")
        ));
        List<SubCategoryDto> results = session.createQuery(criteriaQuery).getResultList();
        return results;
		}

	@Override
	public void deleteSubCategory(long id ) {
		Session session = this.mySessionFactory.getCurrentSession();
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
		Session session = this.mySessionFactory.getCurrentSession();
		String hql  = "FROM SubCategory S WHERE S.category.id = :categoryId";
		Query<SubCategory> query = session.createQuery(hql,SubCategory.class);
		query.setParameter("categoryId", CategoryId);
		List<SubCategory> results = query.list();
		return results;
	}

	@Override
	public List<SubCategory> getSubCategoryInSuchGategory(String CategoryName) {
		Session session = this.mySessionFactory.getCurrentSession();
		String hql = "SELECT s FROM SubCategory s JOIN s.category c WHERE c.categoryName = :categoryName";
		Query<SubCategory> query = session.createQuery(hql,SubCategory.class);
		query.setParameter("categoryName", CategoryName);
		List<SubCategory> results = query.list();
		return results;
	}
	
	
}

