package com.verusys.gourav.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.verusys.gourav.entity.Patient;

/**
 * @author:GOURAV CHOPRA 
 *  Generated F/w:SHWR-Framework 
 */
public interface PatientRepository extends JpaRepository<Patient, Long> {
	
	Optional<Patient> findByEmail(String email);
}
