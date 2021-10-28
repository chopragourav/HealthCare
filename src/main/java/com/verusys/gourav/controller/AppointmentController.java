package com.verusys.gourav.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.verusys.gourav.entity.Appointment;
import com.verusys.gourav.entity.Doctor;
import com.verusys.gourav.exception.AppointmentNotFoundException;
import com.verusys.gourav.exception.DoctorNotFoundException;
import com.verusys.gourav.service.IAppointmentService;
import com.verusys.gourav.service.IDoctorService;
import com.verusys.gourav.service.ISpecializationService;

@Controller
@RequestMapping("/appointment")
public class AppointmentController {

	@Autowired
	private IAppointmentService service;

	@Autowired
	private IDoctorService doctorService;

	@Autowired
	private ISpecializationService specializationService;

	private void createDynamicUI(Model model) {
		model.addAttribute("doctors", doctorService.getDocIdAndName());
	}

	@GetMapping("/register")
	public String registerAppointment(@RequestParam(value = "message", required = false) String message, Model model) {
		model.addAttribute("message", message);
		createDynamicUI(model);
		return "AppointmentRegister";
	}

	@PostMapping("/save")
	public String saveAppointment(@ModelAttribute Appointment appointment, Model model) {
		Long id = service.saveAppointment(appointment);
		model.addAttribute("message", "Appointment with (" + id + ") is created");
		return "AppointmentRegister";
	}

	@GetMapping("/all")
	public String viewAll(Model model, @RequestParam(value = "message", required = false) String message) {
		List<Appointment> list = service.getAllAppointment();
		model.addAttribute("list", list);
		model.addAttribute("message", message);
		return "AppointmentData";
	}

	@GetMapping("/delete")
	public String deleteAppointment(@RequestParam("id") Long id, RedirectAttributes attribute) {
		try {
			service.removeAppointment(id);
			attribute.addAttribute("message", "Record with (" + id + ") is removed");
		} catch (AppointmentNotFoundException e) {
			e.printStackTrace();
			attribute.addAttribute("message", e.getMessage());
		}
		return "redirect:all";
	}

	@GetMapping("/edit")
	public String showEditPage(@RequestParam Long id, Model model, RedirectAttributes attributes) {
		String page = null;
		try {
			Appointment doc = service.getOneAppointment(id);
			model.addAttribute("appointment", doc);
			createDynamicUI(model);
			page = "AppointmentEdit";
		} catch (DoctorNotFoundException e) {
			e.printStackTrace();
			attributes.addAttribute("message", e.getMessage());
			page = "redirect:all";
		}
		return page;
	}

	@PostMapping("/update")
	public String updateData(@ModelAttribute Appointment appointment, RedirectAttributes attribute) {
		service.updateAppointment(appointment);
		attribute.addAttribute("message", "Record with (" + appointment.getId() + ") is updated");
		return "redirect:all";
	}

	// .. view appointments page..
	@GetMapping("/view")
	public String viewSlots(@RequestParam(required = false, defaultValue = "0") Long specId, Model model) {
		// fetch data for Spec DropDown
		Map<Long, String> specMap = specializationService.getSpecIdAndName();
		model.addAttribute("specializations", specMap);

		List<Doctor> docList = null;
		String message = null;
		if (specId <= 0) { // if they did not select any spec
			docList = doctorService.getAllDoctors();
			message = "Result : All Doctors";
		} else {
			docList = doctorService.findDoctorBySpecName(specId);
			message = "Result : " + specializationService.getOneSpecialization(specId).getSpecName() + " Doctors";
		}
		model.addAttribute("docList", docList);

		model.addAttribute("message", message);

		return "AppointmentSearch";
	}

	// .. view slots...
	@GetMapping("/viewSlot")
	public String showSlots(@RequestParam Long id, Model model) {
		// fetch apps based on doctor id
		List<Object[]> list = service.getAppoinmentsByDoctor(id);
		model.addAttribute("list", list);
		Doctor doc = doctorService.getOneDoctor(id);
		model.addAttribute("message", "RESULTS SHOWING FOR : " + doc.getFirstName() + " " + doc.getLastName());
		return "AppointmentSlots";
	}
}
