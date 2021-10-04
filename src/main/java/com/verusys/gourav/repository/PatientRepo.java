package com.verusys.gourav.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.verusys.gourav.entity.Patient;

public interface PatientRepo extends JpaRepository<Patient, Long> {
}
