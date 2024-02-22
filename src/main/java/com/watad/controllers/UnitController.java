package com.watad.controllers;

import java.util.List;

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

import com.watad.Dao.UnitDao;
import com.watad.model.Unit;



@Transactional
@RestController
public class UnitController {
	
	@Autowired
	private UnitDao unitDao; 
	
	@GetMapping(path = "/unitPage")
	public ModelAndView retrivingUnitPage(ModelAndView modelAndView) {
		List<Unit>allUnits = unitDao.getAllUnit();
		modelAndView.addObject("allUnits",allUnits);
		modelAndView.setViewName("unit");
		return modelAndView;
		
	}
	
	@PostMapping("/addUnit")
	public ModelAndView addUnit(Unit unit) {
		ModelAndView modelAndView  ;
		modelAndView = new ModelAndView("redirect:/unitPage");
		if(unitDao.findByName(unit.getUnitName())) {
			String message = " Unit name is aready saved before *";
			modelAndView.addObject("errMessage", message);
			return modelAndView;
		}
		unitDao.addUnit(unit);
		return modelAndView;		
	}
	
	@DeleteMapping("/deleteUnit/{id}")
	public Unit deleteUnit(@PathVariable Long id) {
		return unitDao.DeleteUnit(id);	
	}

	@PutMapping("/editUnit")
	public ResponseEntity<?> editUnit(@RequestBody Unit unit) {
		
		if(unitDao.findByName(unit.getUnitName())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Category Name aready exists");
		}
			return ResponseEntity.ok(unitDao.EditUnit(unit));		
	}
}
