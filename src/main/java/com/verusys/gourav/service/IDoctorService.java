package com.verusys.gourav.service;

import java.util.List;
import java.util.Map;

import com.verusys.gourav.entity.Doctor;

public interface IDoctorService {
	public Long saveDoctor(Doctor doc);
	public List<Doctor> getAllDoctors();
	public void removeDoctor(Long id);
	public Doctor getOneDoctor(Long id);
	public void updateDoctor(Doctor doc);
	
	/*public boolean isdocCodeExist(String docCode);
	
	public boolean isdocCodeExistForEdit(String docCode, Long id);*/
	
//	public boolean isdocCodeExistForEdit(String docCode, Long id);
	
	
	//public boolean isdocNameExist(String docName);
	Map<Long, String> getDocIdAndName();
}
