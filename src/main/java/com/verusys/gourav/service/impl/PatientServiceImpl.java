package com.verusys.gourav.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verusys.gourav.entity.Patient;
import com.verusys.gourav.exception.PatientNotFoundException;
import com.verusys.gourav.repository.PatientRepo;
import com.verusys.gourav.service.IPatientService;

@Service
public class PatientServiceImpl implements IPatientService {

	@Autowired
	private PatientRepo repo;

	@Override
	public Long savePatient(Patient patient) {
		return repo.save(patient).getId();
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
