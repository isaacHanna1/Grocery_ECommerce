package com.watad.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.watad.services.UnitService;
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

import com.watad.Dao.UnitDao;
import com.watad.model.Unit;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@RestController
public class UnitController {
	
	private final UnitService unitService;

	public UnitController(UnitService unitService) {
		this.unitService = unitService;
	}

	@GetMapping(path = "/unitPage")
	public ModelAndView retrivingUnitPage(ModelAndView modelAndView) {
		List<Unit>allUnits = unitService.getAllUnit();
		modelAndView.addObject("allUnits",allUnits);
		modelAndView.setViewName("unit");
		return modelAndView;
		
	}

	@PostMapping("/addUnit")
	public ModelAndView addUnit(Unit unit , RedirectAttributes redirectAttributes) {
		if(unitService.findByName(unit.getUnitName())) {
			String message = " هذة الوحدة مسجلة من قبل الرجاء تغير الاسم";
			redirectAttributes.addFlashAttribute("errMessage", message);
			return new ModelAndView("redirect:/unitPage");
		}
		unitService.addUnit(unit);
		return new ModelAndView("redirect:/unitPage");
	}

	@DeleteMapping("/deleteUnit/{id}")
	public ResponseEntity<Map<String, String>> deleteUnit(@PathVariable Long id) {
		try {
			Unit unit = unitService.DeleteUnit(id);
			Map<String, String> response = new HashMap<>();
			response.put("message", "تم حذف الوحدة بنجاح");
			response.put("status", "pass");
			return ResponseEntity.ok(response);
		} catch (RuntimeException ex) {
			Map<String, String> error = new HashMap<>();
			error.put("message", "لا يمكن حذف الوحدة لأنها مستخدمة في مكان آخر");
			error.put("status","failed");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
		}catch (Exception ex){
			Map<String, String> error = new HashMap<>();
			error.put("message", "لا يمكن حذف الوحدة لأنها مستخدمة في مكان آخر");
			error.put("status","failed");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
		}
	}



	@PutMapping("/editUnit")
	public ResponseEntity<?> editUnit(@RequestBody Unit unit) {
		
		if(unitService.findByName(unit.getUnitName())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("هذة الوحدة مسجلة من قبل !");
		}
			return ResponseEntity.ok(unitService.EditUnit(unit));
	}
}
