package com.verusys.gourav.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.verusys.gourav.entity.Appointment;

public interface AppointmentRepo extends JpaRepository<Appointment, Long> {

	@Query("SELECT aptm.date, aptm.noOfSlots, aptm.fee FROM Appointment aptm INNER JOIN aptm.doctor as doctor WHERE doctor.id=:docId")
	public List<Object[]> getAppoinmentsByDoctor(Long docId);
}
