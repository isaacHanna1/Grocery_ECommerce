package com.watad.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.watad.Dao.CategoryDao;
import com.watad.model.Category;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Transactional
@RestController
public class CategoryController {

	// this instance,initalized by spring container
	// to use db function in categoryDaoImp
	@Autowired
	private CategoryDao categoryDao; 
	
	// this function for geting jsp page for category page
	@GetMapping(path = "/categoryPage")
	public ModelAndView retrivingCategoryPage(ModelAndView modelAndView) {
		List<Category>allCategories = categoryDao.getListOfCategory();
		modelAndView.setViewName("addsection");
		modelAndView.addObject("allCategories",allCategories);
		return modelAndView;
	}

	// function for saving new category  
	@PostMapping("/addCategory")
	public ModelAndView AddCategory(Category category ) {
		ModelAndView modelAndView  ;
		modelAndView = new ModelAndView("redirect:/categoryPage");
		if(existsInDataBase(category)) {
			String message = " category name is aready saved before *";
			modelAndView.addObject("errMessage", message);
			return modelAndView;		
		}
		categoryDao.insertNewCategory(category);
		return modelAndView;		
	}
	/* this api function to delete category from database
	 * i will use  by javascript to delete the record 
	*/
	@DeleteMapping("/categoryApi/deleteCategory/{id}")
	public Category deleteCategory(@PathVariable Long id) {
		Category deletedCategory = categoryDao.getCategory(id);
	    categoryDao.deleteCategory(id);
	    return deletedCategory;
	}
	
	@PutMapping("/categoryApi/editCategory")
	public ResponseEntity<?> updateCategory(@RequestBody Category category  ) {
		
		if(existsInDataBase(category)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Category Name aready exists");
		}
			return ResponseEntity.ok(categoryDao.editCategory(category));	
	}

	// i check if category that i want insert/update in database exists or not 
	public boolean existsInDataBase(Category category) {

		List<Category> allCategories = categoryDao.getListOfCategory();
		Set <String> categoryNames = new HashSet<>();
		for(Category c : allCategories) {
			categoryNames.add(c.getCategoryName());
		}
		if(categoryNames.contains(category.getCategoryName())) {
				return true;
		}else {
			    return false;
		}
	}
	@GetMapping(path = "/allMainCategoy")
	public List<Category> gettAllCategory() {
		return categoryDao.getListOfCategory();
	}
}
