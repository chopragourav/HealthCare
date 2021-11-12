package com.verusys.gourav.service;

import java.util.List;

import com.verusys.gourav.entity.Patient;

/**
 * @author:GOURAV CHOPRA 
 *  Generated F/w:SHWR-Framework 
 */
public interface IPatientService {
	Long savePatient(Patient patient);

	void updatePatient(Patient patient);

	void deletePatient(Long id);

	Patient getOnePatient(Long id);

	List<Patient> getAllPatients();
	
	Patient getOneByEmail(String email);
	
	long getPatientCount();
}
