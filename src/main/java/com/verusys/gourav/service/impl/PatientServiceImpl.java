package com.verusys.gourav.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verusys.gourav.constant.UserRoles;
import com.verusys.gourav.entity.Patient;
import com.verusys.gourav.entity.User;
import com.verusys.gourav.exception.PatientNotFoundException;
import com.verusys.gourav.repository.PatientRepo;
import com.verusys.gourav.service.IPatientService;
import com.verusys.gourav.service.IUserService;
import com.verusys.gourav.util.MyMailUtil;
import com.verusys.gourav.util.UserUtil;

@Service
public class PatientServiceImpl implements IPatientService {

	@Autowired
	private PatientRepo repo;

	@Autowired
	private IUserService userservice;

	@Autowired
	private UserUtil util;

	@Autowired
	private MyMailUtil mailUtil;

	@Override
	public Long savePatient(Patient patient) {
		Long id = repo.save(patient).getId();
		if (id != null) {
			String pwd = util.genPwd();
			User user = new User();
			user.setDisplayName(patient.getFirstName() + " " + patient.getLastName());
			user.setUserName(patient.getEmail());
			user.setPassword(pwd);
			user.setRole(UserRoles.PATIENT.name());
			Long genId = userservice.saveUser(user);
			if (genId != null)
				new Thread(new Runnable() {

					@Override
					public void run() {
						String text = "Your username is "+patient.getEmail()+" and password is "+pwd;
						mailUtil.send(patient.getEmail(), "Patient Added", text);
					}
				}).start();
		}
		return id;
	}

	@Override
	public List<Patient> getAllPatients() {
		return repo.findAll();
	}

	@Override
	public void removePatient(Long id) {
		repo.delete(getOnePatient(id));
	}

	@Override
	public Patient getOnePatient(Long id) {
		return repo.findById(id).orElseThrow(() -> new PatientNotFoundException("Patient with " + id + " not found"));
	}

	@Override
	public void updatePatient(Patient patient) {
		if (repo.existsById(patient.getId()))
			repo.save(patient);
		else
			throw new PatientNotFoundException(patient.getId() + " , no found");
	}
}
