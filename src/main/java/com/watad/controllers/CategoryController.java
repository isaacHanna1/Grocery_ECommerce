package com.watad.controllers;


import com.watad.services.CategoryService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.watad.Dao.CategoryDao;
import com.watad.model.Category;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class CategoryController {

	private final CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	// this function for geting jsp page for category page
	@GetMapping(path = "/categoryPage")
	public ModelAndView retrivingCategoryPage(ModelAndView modelAndView) {
		List<Category>allCategories = categoryService.getListOfCategory();
		modelAndView.setViewName("addsection");
		modelAndView.addObject("allCategories",allCategories);
		return modelAndView;
	}

	// function for saving new category  
	@PostMapping("/addCategory")
	public ModelAndView AddCategory(Category category , RedirectAttributes redirectAttributes) {
		if(existsInDataBase(category)) {
			String message = "هناك بالفعل قسم بهذا الاسم ";
			redirectAttributes.addFlashAttribute("errMessage",message);
			return new ModelAndView("redirect:/categoryPage");
		}
		categoryService.insertNewCategory(category);
		return new ModelAndView("redirect:/categoryPage");
	}
	/* this api function to delete category from database
	 * i will use  by javascript to delete the record 
	*/
	@DeleteMapping("/categoryApi/deleteCategory/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
	   Category category = categoryService.getCategory(id);
	    
	    if (category == null) {
	        return ResponseEntity.notFound().build();
	    }
	    
	    // Check if there are associated subcategories
	    if (!category.getSubCategory().isEmpty()) {
	        return ResponseEntity.badRequest().body("لا يمكنك مسح القسم لانه مرتبط باقسام فرعية ");
	    }
	    
	    categoryService.deleteCategory(id);
	    
	    return ResponseEntity.ok().body(category);
	}
	
	@PutMapping("/categoryApi/editCategory")
	public ResponseEntity<?> updateCategory(@RequestBody Category category  ) {
		
		if(existsInDataBase(category)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("هناك بالفعل قسم بهذا الاسم ");
		}
			return ResponseEntity.ok(categoryService.editCategory(category));
	}

	// i check if category that i want insert/update in database exists or not 
	public boolean existsInDataBase(Category category) {

		List<Category> allCategories = categoryService.getListOfCategory();
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
		return categoryService.getListOfCategory();
	}
}
