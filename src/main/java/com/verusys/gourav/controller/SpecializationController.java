package com.verusys.gourav.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.verusys.gourav.entity.Specialization;
import com.verusys.gourav.service.ISpecializationService;

@Controller
@RequestMapping("/spec")
public class SpecializationController {
	@Autowired
	private ISpecializationService service;

	/**
	 * 1. show specialization Form
	 */
	@GetMapping("/register")
	public String displayRegister() {
		return "specializationRegister";
	}

	@PostMapping("/save")
	public String saveForm(@ModelAttribute Specialization Specialization, 
											Model model) {
		Long id = service.saveSpecialization(Specialization);
		String message = "Record id (" + id + ") is created";
		model.addAttribute("message", message);
		return "SpecializationRegister";
	}
	
	@GetMapping("/all")
	public String viewAll(Model model,
										@RequestParam(value = "message", required = false) String message) {
		List<Specialization> specList = service.getAllSpecializations();
		model.addAttribute("specList", specList);
		model.addAttribute("message", message);
		return "SpecializationData";
	}
}
