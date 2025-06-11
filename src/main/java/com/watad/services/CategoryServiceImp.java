package com.watad.services;


import com.watad.Dao.CategoryDao;
import com.watad.model.Category;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.Collections;
import java.util.List;

@Service
public class CategoryServiceImp implements  CategoryService{


    private final CategoryDao categoryDao;

    public CategoryServiceImp(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    @Transactional
    public void insertNewCategory(Category category) {
            categoryDao.insertNewCategory(category);
    }

    @Override
    @Transactional(readOnly = true)
    public Category getCategory(long categoryId) {
        Category category =  categoryDao.getCategory(categoryId);
        if(category == null){
            throw new NoResultException("There No Category with this Id");
        }
        return category;
    }

    @Override
    @Transactional
    public Category editCategory(Category category) {
         categoryDao.editCategory(category);
         return  category;
    }

    @Override
    @Transactional
    public Category deleteCategory(long categoryId) {
         Category category =  getCategory(categoryId);
         if(category == null){
             throw new NoResultException("There No Category with this Id");
         }
         return  categoryDao.deleteCategory(categoryId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> getListOfCategory() {
        List<Category> allCategories = categoryDao.getListOfCategory();
        return allCategories;
    }


}
