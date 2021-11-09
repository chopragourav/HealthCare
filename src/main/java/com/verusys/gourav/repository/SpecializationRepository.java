package com.verusys.gourav.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.verusys.gourav.entity.Specialization;

public interface SpecializationRepository extends JpaRepository<Specialization, Long> {

	@Query("SELECT COUNT(specCode) FROM Specialization  WHERE specCode=:specCode")
	Integer getSpecCodeCount(String specCode);
	
	@Query("SELECT COUNT(specCode) FROM Specialization  WHERE specCode=:specCode AND id!=:id")
	Integer getSpecCodeCountForEdit(String specCode,Long id);
	
	@Query("SELECT id,specName FROM Specialization ")
	List<Object[]> getSpecIdAndName();
	
}
