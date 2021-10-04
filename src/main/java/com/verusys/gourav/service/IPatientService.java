package com.verusys.gourav.service;

import java.util.List;

import com.verusys.gourav.entity.Patient;
import com.verusys.gourav.entity.Patient;

public interface IPatientService {
	public Long savePatient(Patient patient);
	public List<Patient> getAllPatients();
	public void removePatient(Long id);
	public Patient getOnePatient(Long id);
	public void updatePatient(Patient patient);
	
	/*public boolean isdocCodeExist(String docCode);
	
	public boolean isdocCodeExistForEdit(String docCode, Long id);*/
	
//	public boolean isdocCodeExistForEdit(String docCode, Long id);
	
	
	//public boolean isdocNameExist(String docName);
}
