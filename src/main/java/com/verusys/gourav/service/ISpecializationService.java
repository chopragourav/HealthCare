package com.verusys.gourav.service;

import java.util.List;

import com.verusys.gourav.entity.Specialization;

public interface ISpecializationService {
	public Long saveSpecialization(Specialization spec);
	public List<Specialization> getAllSpecializations();
	public void removeSpecialization(Long id);
	public Specialization getOneSpecialization(Long id);
	public void updateSpecialization(Specialization spec);
	
	public boolean isSpecCodeExist(String specCode);
	
	public boolean isSpecCodeExistForEdit(String specCode, Long id);
	
//	public boolean isSpecCodeExistForEdit(String specCode, Long id);
	
	
	//public boolean isSpecNameExist(String specName);
}
