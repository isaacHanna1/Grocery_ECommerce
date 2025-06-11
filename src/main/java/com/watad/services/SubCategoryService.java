package com.watad.services;

import com.watad.Dto.SubCategoryDto;
import com.watad.model.SubCategory;

import java.util.List;

public interface SubCategoryService {
    public SubCategory insertNewSubCategory(SubCategory subCategory);
    public List<SubCategoryDto> allSubCategories();
    public void deleteSubCategory(long id);
    public boolean findByName(SubCategory subCategory);
    public SubCategory editSubCategory(SubCategory subCategory);
    public List<SubCategory> getSubCategoryInSuchGategory(long CategoryId);
    public List<SubCategory> getSubCategoryInGategory(String CategoryName);
    SubCategory findById(long id);

}
