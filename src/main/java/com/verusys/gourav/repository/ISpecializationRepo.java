package com.verusys.gourav.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.verusys.gourav.entity.Specialization;

public interface ISpecializationRepo extends JpaRepository<Specialization, Long> {
	
	/*@Query("SELECT COUNT(specCode) FROM Specialization WHERE specCode=:specCode")
	Integer getSpecCodeCount(String specCode);*/
	
	@Query("SELECT COUNT(specCode) FROM Specialization  WHERE specCode=:specCode")
	Integer getSpecCodeCount(String specCode);
	
	@Query("SELECT COUNT(specName) FROM Specialization  WHERE specName=:specName")
	Integer getSpecNameCount(String specName);
}
