package com.verusys.gourav.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

	/**
	 * 2. On Submit Form save data
	 * 
	 */
	@PostMapping("/save")
	public String saveForm(@ModelAttribute Specialization Specialization, Model model) {
		Long id = service.saveSpecialization(Specialization);
		String message = "Record id (" + id + ") is created";
		model.addAttribute("message", message);
		return "SpecializationRegister";
	}

	/**
	 * 3. display all Specialization
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/all")
	public String viewAll(Model model, @RequestParam(value = "message", required = false) String message) {
		List<Specialization> specList = service.getAllSpecializations();
		model.addAttribute("list", specList);
		model.addAttribute("message", message);
		return "SpecializationData";
	}

	/**
	 * 4. delete by if
	 * 
	 * @param id
	 * @param attribute
	 * @return
	 */
	@GetMapping("/delete")
	public String deleteSpec(@RequestParam Long id, RedirectAttributes attribute) {
		service.removeSpecialization(id);
		attribute.addAttribute("message", "Record id (" + id + ") is removed");
		return "redirect:all";
	}

	/**
	 * 5. fetch data in to edit page
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/edit")
	public String showEditPage(@RequestParam Long id, Model model) {
		Specialization spec = service.getOneSpecialization(id);
		model.addAttribute("specialization", spec);
		return "SpecializationEdit";
	}

	/**
	 * 6. update data
	 * 
	 * @param specialization
	 * @param attribute
	 * @return
	 */
	@PostMapping("/update")
	public String updateData(@ModelAttribute Specialization specialization, RedirectAttributes attribute) {
		service.updateSpecialization(specialization);
		attribute.addAttribute("message", "Record id (" + specialization.getId() + ") is updated");
		return "redirect:all";
	}

	/**
	 * 7. read code and check with service and return back to same ui
	 */
	@GetMapping("/checkCode")
	@ResponseBody
	public String validateSpecCode(@RequestParam String code) {
		String message = "";
		if (service.isSpecCodeExist(code)) {
			message = "* " + code + ", already exist";
		}
		return message; // this is not viewName(it is message)
	}

	/**
	 * 7. read Name and check with service and return back to same ui
	 */
	@GetMapping("/checkName")
	@ResponseBody
	public String validateSpecName(@RequestParam String name) {
		String message = "";
		if (service.isSpecNameExist(name)) {
			message = "* " + name + " , already exist";
		}
		return message;
	}
}
