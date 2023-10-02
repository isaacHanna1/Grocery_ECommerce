package com.watad.Dao;

import com.watad.Dto.SubCategoryDto;
import com.watad.model.SubCategory;
import java.util.List;

public interface SubCategoryDao {

	public SubCategory insertNewCategory(SubCategory subCategory);
	public List<SubCategoryDto> allSubCategories();
	public void deleteSubCategory(long id);
	public long newId();
}
