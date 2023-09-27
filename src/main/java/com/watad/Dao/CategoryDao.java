package com.watad.Dao;

import com.watad.model.Category;
import java.util.List;
public interface CategoryDao {
	
	public void insertNewCategory(Category category);
	public Category getCategory(long categoryId );
	public Category editCategory(Category category);
	public Category deleteCategory(long categoryId);
	public List<Category>getListOfCategory();
//	

	
}
