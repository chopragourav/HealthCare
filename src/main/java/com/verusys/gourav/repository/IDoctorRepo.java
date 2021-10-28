package com.verusys.gourav.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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
	@Query("SELECT id,firstName,lastName FROM Doctor")
	List<Object[]> getDocIdAndName();

	@Query("SELECT doct FROM Doctor doct INNER JOIN doct.specialization as spc WHERE spc.id=:specId")
	public List<Doctor> findDoctorBySpecName(Long specId);
}
