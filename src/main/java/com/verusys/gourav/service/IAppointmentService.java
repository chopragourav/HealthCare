package com.verusys.gourav.service;

import java.util.List;

import com.verusys.gourav.entity.Appointment;

public interface IAppointmentService {
	public Long saveAppointment(Appointment app);
	public List<Appointment> getAllAppointment();
	public void removeAppointment(Long id);
	public Appointment getOneAppointment(Long id);
	public void updateAppointment(Appointment app);
}
