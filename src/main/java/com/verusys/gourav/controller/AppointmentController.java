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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.verusys.gourav.entity.Appointment;
import com.verusys.gourav.entity.Doctor;
import com.verusys.gourav.exception.AppointmentNotFoundException;
import com.verusys.gourav.exception.DoctorNotFoundException;
import com.verusys.gourav.service.IAppointmentService;
import com.verusys.gourav.service.IDoctorService;

import net.bytebuddy.asm.Advice.OffsetMapping.ForOrigin.Renderer.ForReturnTypeName;

@Controller
@RequestMapping("/appointment")
public class AppointmentController {
	
	@Autowired
	private IAppointmentService service;
	
	@Autowired
	private IDoctorService doctorService;
	
	private void createDynamicUI(Model model) {
		model.addAttribute("doctors", doctorService.getDocIdAndName());
	}
	
	@GetMapping("/register")
	public String registerAppointment(@RequestParam(value = "message", required = false)String message,
																Model model) {
		model.addAttribute("message", message);
		createDynamicUI(model);
		return "AppointmentRegister";
	}
	
	@PostMapping("/save")
	public String saveAppointment(@ModelAttribute Appointment appointment,
														Model model) {
		Long id=service.saveAppointment(appointment);
		model.addAttribute("message", "Appointment with ("+id+") is created");
		return "AppointmentRegister";
	}
	
	@GetMapping("/all")
	public String viewAll(Model model, 
			@RequestParam(value = "message", required = false) String message) {
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
}
