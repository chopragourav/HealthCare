package com.verusys.gourav.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.verusys.gourav.entity.Doctor;

public interface IDoctorRepo extends JpaRepository<Doctor, Long> {
	
	/*@Query("SELECT COUNT(specCode) FROM Specialization WHERE specCode=:specCode")
	Integer getSpecCodeCount(String specCode);*/
	
	/*@Query("SELECT COUNT(specCode) FROM Specialization  WHERE specCode=:specCode")
	Integer getSpecCodeCount(String specCode);*/
	
	/*@Query("SELECT COUNT(specName) FROM Specialization  WHERE specName=:specName")
	Integer getSpecNameCount(String specName);*/
	
	/*@Query("SELECT COUNT(specCode) FROM Specialization  WHERE specCode=:specCode AND id!=:id")
	Integer getSpecCodeCountForEdit(String specCode, Long id);*/
}