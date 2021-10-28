package com.verusys.gourav.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.verusys.gourav.entity.Doctor;
import com.verusys.gourav.exception.DoctorNotFoundException;
import com.verusys.gourav.service.IDoctorService;
import com.verusys.gourav.service.ISpecializationService;
import com.verusys.gourav.util.MyMailUtil;

@Controller
@RequestMapping("/doctor")
public class DoctorController {
	@Autowired
	private IDoctorService service;
	
	@Autowired
	private MyMailUtil mailUtil;
	
	@Autowired
	private ISpecializationService specializationService;

	private void createDynamicUI(Model model) {
		model.addAttribute("specializations", specializationService.getSpecIdAndName());
	}
	/**
	 * 1. show Doctor Form
	 */
	@GetMapping("/register")
	public String displayRegister(@RequestParam(value = "message", required = false) String message, Model model) {
		model.addAttribute("message", message);
		createDynamicUI(model);
		return "doctorRegister";
	}

	/**
	 * 2. On Submit Form save data
	 * 
	 */
	/*@PostMapping("/save")
	public String saveForm(@ModelAttribute Doctor doctor, 
			@RequestParam("docImg") MultipartFile multipartFile,
			Model model) {
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		doctor.setPhotos(fileName);
	
		Long id = service.saveDoctor(doctor);
		model.addAttribute("message", "Record id (" + id + ") is created");
		String uploadDir = "user-photos/" + id;
		try {
			FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "DoctorRegister";
	}*/

	@PostMapping("/save")
	public String saveForm(@ModelAttribute Doctor Doctor, 
			Model model) {
		Long id = service.saveDoctor(Doctor);
		String message = "Doctor ("+id+") is created";
		model.addAttribute("message", message);
		if(id!=null) {
			new Thread(new Runnable() {
				public void run() {
					mailUtil.send(
							Doctor.getEmail(), 
							"Doctor created successfully", 
							message,
							new ClassPathResource("/static/myres/sample.pdf"));
				}
			}).start();
		}
		return "DoctorRegister";
	}

	/**
	 * 3. display all Doctor
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/all")
	public String viewAll(Model model, 
			@RequestParam(value = "message", required = false) String message) {
		List<Doctor> list = service.getAllDoctors();
		model.addAttribute("list", list);
		model.addAttribute("message", message);
		return "DoctorData";
	}

	/**
	 * 4. delete by if
	 * 
	 * @param id
	 * @param attribute
	 * @return
	 */
	@GetMapping("/delete")
	public String deleteSpec(@RequestParam("id") Long id, RedirectAttributes attribute) {
		try {
			service.removeDoctor(id);
			attribute.addAttribute("message", "Record with (" + id + ") is removed");
		} catch (DoctorNotFoundException e) {
			e.printStackTrace();
			attribute.addAttribute("message", e.getMessage());
		}
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
	public String showEditPage(@RequestParam("id") Long id, Model model, RedirectAttributes attributes) {
		String page = null;
		try {
			Doctor doc = service.getOneDoctor(id);
			model.addAttribute("doctor", doc);
			createDynamicUI(model);
			page = "DoctorEdit";
		} catch (DoctorNotFoundException e) {
			e.printStackTrace();
			attributes.addAttribute("message", e.getMessage());
			page = "redirect:all";
		}
		return page;
	}

	/**
	 * 6. update data
	 * 
	 * @param Doctor
	 * @param attribute
	 * @return
	 */
	@PostMapping("/update")
	public String updateData(@ModelAttribute Doctor doctor, RedirectAttributes attribute) {
		service.updateDoctor(doctor);
		attribute.addAttribute("message", "Record with (" + doctor.getId() + ") is updated");
		return "redirect:all";
	}

	/**
	 * 7. read code and check with service and return back to same ui
	 */
	/*@GetMapping("/checkCode")
	public String validSpecCode(@RequestParam String code, @RequestParam("id") Long id) {
		String message="";
			if(id==0 && service.isSpecCodeExist(code)) {
				message=code+" , already exist";
			}else if(id!=0 && service.isSpecCodeExistForEdit(code, id)) {
				message=code+" , already exist";
			}
		return message;
	}*/
	/*@GetMapping("/checkCode")
	@ResponseBody
	public String validateSpecCode(@RequestParam String code) {
	String message = "";
	if (service.isSpecCodeExist(code)) {
		//if(id==0 && service.isSpecCodeExist(code)) {
		message = "* " + code + ", already exist";
	} else if(id!=0 && service.isSpecCodeExistForEdit(code, id)){
		message = "* " + code + ", already exist";
		}
	//}
	return message; // this is not viewName(it is message)
	}*/

	/*@GetMapping("/checkCode")
	@ResponseBody
	public String validateSpecCode(@RequestParam String code, @RequestParam Long id) {
		String message = "";
		if (service.isSpecCodeExist(code)) {
			if (id == 0 && service.isSpecCodeExist(code)) {
				message = "* " + code + ", already exist";
			} else if (id != 0 && service.isSpecCodeExistForEdit(code, id)) {
				message = "* " + code + ", already exist";
			}
		}
		return message; // this is not viewName(it is message)
	}*/

	/**
	 * 8. read Name and check with service and return back to same ui
	 */
	/*@GetMapping("/checkName")
	@ResponseBody
	public String validateSpecName(@RequestParam String name) {
		String message = "";
		if (service.isSpecNameExist(name)) {
			message = "* " + name + " , already exist";
		}
		return message;
	}*/

	/***
	 * 9. export data to excel file
	 */
	/*@GetMapping("/excel")
	public ModelAndView exportToExcel() {
		ModelAndView mav = new ModelAndView();
		mav.setView(new DoctorExcelView());
	
		// read data from DB
		List<Doctor> list = service.getAllDoctors();
		// send to Excel Impl class
		mav.addObject("list", list);
	
		return mav;
	}*/
}
