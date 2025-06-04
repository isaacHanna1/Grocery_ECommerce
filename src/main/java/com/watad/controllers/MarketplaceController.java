package com.watad.controllers;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.watad.Dao.CategoryDao;
import com.watad.Dao.SubCategoryDao;
import com.watad.Dao.itemDao;
import com.watad.Dto.ItemDto;
import com.watad.model.Category;
import com.watad.model.Item;
import com.watad.model.SubCategory;


@Transactional
@RestController
public class MarketplaceController {
    private static final Logger logger = LogManager.getLogger(MarketplaceController.class);

	
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private SubCategoryDao subCategoryDao;
	
	@Autowired
	private itemDao itemDao;
	
	@Autowired
	private itemController item_controller;

	
	@GetMapping("/marketPlace")
	public ModelAndView getMarketPlace(ModelAndView modelAndView) {
		
		logger.info("Just Started The Home Page");
		
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();        
        String role = auth.getAuthorities().stream().findFirst().orElse(null).getAuthority();

        if(role.equals("ROlE_TRADER")) {
        	return  getMarketPlace_trader(modelAndView) ;
        }
        
        
        else if(role.equals("ROLE_ADMIN")) {
        	return item_controller.retrivingItemPage(modelAndView);
        }
		List<Category> allCategories = categoryDao.getListOfCategory();
		List<Item>     NewArrival    = itemDao.getNewArrival();
		List<Item>     highDiscount    = itemDao.getHighDiscounts();
		modelAndView.setViewName("index");
		modelAndView.addObject("allCategories", allCategories);
		modelAndView.addObject("newArrival"   , NewArrival);
		modelAndView.addObject("highDiscount"   , highDiscount);
		return modelAndView;
	}


	@GetMapping("/marketPlace/user")
	public ModelAndView getMarketPlace_USER(ModelAndView modelAndView) {
		List<Category> allCategories = categoryDao.getListOfCategory();
		List<Item>     NewArrival    = itemDao.getNewArrival();
		List<Item>     highDiscount  = itemDao.getHighDiscounts();
		modelAndView.setViewName("index");
		modelAndView.addObject("allCategories", allCategories);
		modelAndView.addObject("newArrival"   , NewArrival);
		modelAndView.addObject("highDiscount"   , highDiscount);
		return modelAndView;
	}

	@GetMapping("/marketPlace_trader")
	public ModelAndView getMarketPlace_trader(ModelAndView modelAndView) {
		List<Category> allCategories = categoryDao.getListOfCategory();
		List<Item>     NewArrival    = itemDao.getNewArrival();
		List<Item>     highDiscount    = itemDao.getHighDiscounts_trader();
		modelAndView.setViewName("index_trader");
		modelAndView.addObject("allCategories", allCategories);
		modelAndView.addObject("newArrival"   , NewArrival);
		modelAndView.addObject("highDiscount"   , highDiscount);
		return modelAndView;
	}
	@GetMapping("/subCategory/{categoryId}")
	public List<SubCategory> gettingSubCategory(@PathVariable long categoryId){
		List<SubCategory> sub =  subCategoryDao.getSubCategoryInSuchGategory(categoryId);
		if(sub.size() == 0) {
			return null;
		}
		    return sub;
	}
	
	@GetMapping("/items/{pageNumber}/{categoryID}/{subCategoryId}/{role}")
	public List<ItemDto> gettingItemInSubCategory(@PathVariable int pageNumber ,@PathVariable long categoryID ,@PathVariable  long subCategoryId , @PathVariable  String role){
		List<ItemDto> items =  itemDao.getSpecificItemsForCustomers(pageNumber, categoryID, subCategoryId,role);
		return items;
	}

	@GetMapping("/itemsInSuchCategoryAndSubCategory/{categoryId}/{subCategoryId}")
	public long getNumberOfItemsInSuchCategoryAndsubcategory
	(@PathVariable int categoryId , @PathVariable int subCategoryId ) {
		return itemDao.getCountOfRecOfItems(categoryId, subCategoryId);
	}
}
