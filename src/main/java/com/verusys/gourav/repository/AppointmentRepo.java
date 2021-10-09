package com.verusys.gourav.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.verusys.gourav.entity.Appointment;

public interface AppointmentRepo extends JpaRepository<Appointment, Long> {
}
