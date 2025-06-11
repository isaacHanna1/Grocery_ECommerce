package com.watad.controllers;

import com.watad.services.CategoryService;
import com.watad.services.SubCategoryService;
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
import com.watad.Dao.SubCategoryDao;
import com.watad.Dto.SubCategoryDto;
import com.watad.model.Category;
import com.watad.model.SubCategory;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class SubCategoryController {


	private final CategoryService categoryService;

	private final SubCategoryService subCategoryService;

	public SubCategoryController(CategoryService categoryService, SubCategoryService subCategoryService) {
		this.categoryService = categoryService;
		this.subCategoryService = subCategoryService;
	}

	@GetMapping(path = "/subCategory")
	public ModelAndView gettingAllCategory(ModelAndView modelAndView) {
		 List<Category> listOfCategory 			= categoryService.getListOfCategory();
		 List<SubCategoryDto> listOfSubCategory = subCategoryService.allSubCategories();

		 System.out.println("the subCategory size is "+listOfSubCategory);
		 modelAndView.setViewName("addSubSection");
		 modelAndView.addObject("allCategories", listOfCategory);
		 modelAndView.addObject("allSubCategories", listOfSubCategory);
		 return modelAndView;
	}
	
	@PostMapping(path = "/addSubCategory")
	public ResponseEntity<?> addNewSubCategory(@RequestBody SubCategory subCategory) {		
		boolean result = subCategoryService.findByName(subCategory);
			if(result) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Duplicate entry.");
			}
	     	return ResponseEntity.ok(subCategoryService.insertNewSubCategory(subCategory));
			
        
    }
	@GetMapping(path = "/getAllSubCategory")
	public List<SubCategoryDto> gettingAllsubCategory(){
		return subCategoryService.allSubCategories();
	}


	@GetMapping(path = "/getSubCategoriesByCategoryID/{categoryId}")
	public List<SubCategory> getSubCategoriesByCategoryID(@PathVariable long categoryId){
		return subCategoryService.getSubCategoryInSuchGategory(categoryId);
	}


	@DeleteMapping(path = "/deleteSubCategory/{id}")
	public ResponseEntity<Map<String, Object>> deleteSubCategory(@PathVariable long id)  {
		Map<String, Object> response = new HashMap<>();
		try {
			subCategoryService.deleteSubCategory(id);
			response.put("status", "success");
			response.put("message", "SubCategory deleted successfully!");
		} catch (RuntimeException e) {
			response.put("status", "error");
			response.put("message", "Failed to delete SubCategory: " + e.getMessage());
		}
		return ResponseEntity.ok(response);
	}
	

	@PutMapping("/editSubCategory")
	public ResponseEntity<?> updateCategory(@RequestBody SubCategory subCategory ) {
		boolean result = subCategoryService.findByName(subCategory);
		if(result) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("sub Category Name aready exists");
		}
			return ResponseEntity.ok(subCategoryService.editSubCategory(subCategory));
	}
}
