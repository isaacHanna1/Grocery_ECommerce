package com.watad.services;


import com.watad.Dao.SubCategoryDao;
import com.watad.Dto.SubCategoryDto;
import com.watad.model.SubCategory;
import org.eclipse.persistence.annotations.ReadOnly;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class SubCategoryServiceImp implements SubCategoryService{

    private SubCategoryDao subCategoryDao;

    public SubCategoryServiceImp(SubCategoryDao subCategoryDao) {
        this.subCategoryDao = subCategoryDao;
    }

    @Override
    @Transactional
    public SubCategory insertNewSubCategory(SubCategory subCategory) {
        return  subCategoryDao.insertNewSubCategory(subCategory);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SubCategoryDto> allSubCategories() {
        return subCategoryDao.allSubCategories();
    }

    @Override
    @Transactional
    public void deleteSubCategory(long id) {
        SubCategory subCategory =  findById(id);
        if(subCategory.getItems().size()> 0){
            throw new RuntimeException("This Category cannot be deleted as it has linked items. Kindly clear the items first.");
        }
        subCategoryDao.deleteSubCategory(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean findByName(SubCategory subCategory) {
        return subCategoryDao.findByName(subCategory);
    }

    @Override
    @Transactional
    public SubCategory editSubCategory(SubCategory subCategory) {
        return  subCategoryDao.editSubCategory(subCategory);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SubCategory> getSubCategoryInSuchGategory(long CategoryId) {
        return subCategoryDao.getSubCategoryInSuchGategory(CategoryId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SubCategory> getSubCategoryInGategory(String CategoryName) {
        return subCategoryDao.getSubCategoryInGategory(CategoryName);
    }

    @Override
    public SubCategory findById(long id) {
        return  subCategoryDao.findById(id);
    }

}
