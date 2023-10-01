package com.watad.Dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

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
	public SubCategory insertNewCategory(SubCategory subCategory) {
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
}

