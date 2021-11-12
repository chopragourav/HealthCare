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
	public Map<Long,String> getDoctorIdAndNames();
	public List<Doctor> findDoctorBySpecName(Long specId);
	public long getDoctorCount();
}
