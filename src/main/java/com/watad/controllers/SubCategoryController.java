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
import com.watad.Dao.SubCategoryDao;
import com.watad.Dto.SubCategoryDto;
import com.watad.model.Category;
import com.watad.model.SubCategory;

import java.util.List;

@Transactional
@RestController
public class SubCategoryController {

	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private SubCategoryDao subCategoryDao;
	
	@GetMapping(path = "/subCategory")
	public ModelAndView gettingAllCategory(ModelAndView modelAndView) {
		 List<Category> listOfCategory = categoryDao.getListOfCategory();
		 List<SubCategoryDto> listOfSubCategory =subCategoryDao.allSubCategories();
		 modelAndView.setViewName("addSubSection");
		 modelAndView.addObject("allCategories", listOfCategory);
		 modelAndView.addObject("allSubCategories", listOfSubCategory);
		 return modelAndView;
	}
	
	@PostMapping(path = "/addSubCategory")
	public ResponseEntity<?> addNewSubCategory(@RequestBody SubCategory subCategory) {		
		boolean result = subCategoryDao.findByName(subCategory);
			if(result) {
				System.out.println("we not bad ");	
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Duplicate entry.");
			}
	     	return ResponseEntity.ok(subCategoryDao.insertNewSubCategory(subCategory));
			
        
    }
	@GetMapping(path = "/getAllSubCategory")
	public List<SubCategoryDto> gettingAllsubCategory(){
		return subCategoryDao.allSubCategories();
	}
	
	@GetMapping(path = "/getSubCategoriesByCategoryID/{categoryId}")
	public List<SubCategory> getSubCategoriesByCategoryID(@PathVariable long categoryId){
		return subCategoryDao.getSubCategoryInSuchGategory(categoryId);
	}


	@DeleteMapping(path = "/deleteSubCategory/{id}")
	public String deleteSubCategory(@PathVariable long id) {
		subCategoryDao.deleteSubCategory(id);
		String deleted = ""+id;
	
		return "{\"deleted\":"+deleted+"}";
	}
	

	@PutMapping("/editSubCategory")
	public ResponseEntity<?> updateCategory(@RequestBody SubCategory subCategory ) {
		boolean result = subCategoryDao.findByName(subCategory);
		if(result) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("sub Category Name aready exists");
		}
			return ResponseEntity.ok(subCategoryDao.editSubCategory(subCategory));	
	}
}
