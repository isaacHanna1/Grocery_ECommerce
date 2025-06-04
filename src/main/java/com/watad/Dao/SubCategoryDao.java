package com.watad.Dao;

import com.watad.Dto.SubCategoryDto;
import com.watad.model.SubCategory;


import java.util.List;

public interface SubCategoryDao {

	public SubCategory insertNewSubCategory(SubCategory subCategory);
	public List<SubCategoryDto> allSubCategories();
	public void deleteSubCategory(long id);
	public boolean findByName(SubCategory subCategory);
	public SubCategory editSubCategory(SubCategory subCategory);
	public List<SubCategory> getSubCategoryInSuchGategory(long CategoryId);
	public List<SubCategory> getSubCategoryInGategory(String CategoryName);
}
