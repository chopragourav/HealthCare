package com.verusys.gourav.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verusys.gourav.entity.Appointment;
import com.verusys.gourav.exception.AppointmentNotFoundException;
import com.verusys.gourav.repository.AppointmentRepo;
import com.verusys.gourav.service.IAppointmentService;

@Service
public class AppointmentServiceImpl implements IAppointmentService {

	@Autowired
	private AppointmentRepo repo;

	@Override
	public Long saveAppointment(Appointment app) {
		return repo.save(app).getId();
	}

	@Override
	public List<Appointment> getAllAppointment() {
		return repo.findAll();
	}

	@Override
	public void removeAppointment(Long id) {
		repo.delete(getOneAppointment(id));
	}

	@Override
	public Appointment getOneAppointment(Long id) {
		return repo.findById(id)
				.orElseThrow(() -> new AppointmentNotFoundException("Appointment with " + id + " not found"));
	}

	@Override
	public void updateAppointment(Appointment app) {
		if (repo.existsById(app.getId()))
			repo.save(app);
		else
			throw new AppointmentNotFoundException(app.getId() + " , not found");
	}

}
