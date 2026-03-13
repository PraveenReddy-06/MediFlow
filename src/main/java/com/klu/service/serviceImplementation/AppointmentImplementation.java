package com.klu.service.serviceImplementation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klu.model.Appointment;
import com.klu.model.Doctor;
import com.klu.model.Patient;
import com.klu.repo.AppointmentRepo;
import com.klu.service.AppointmentService;

@Service
public class AppointmentImplementation implements AppointmentService {
	
	@Autowired
	private AppointmentRepo appointrepo;
	
	@Override
	public Appointment addAppointment(Appointment app) {
		appointrepo.save(app);
		return app;
	}
	
	@Override
	public LocalDateTime getLeastEndAppointmentTimeOfDoctor(int id) {
		return appointrepo.getLeastEndAppointmentTime(id);
	}

	@Override
	public List<Appointment> getAllAppointmentsOfDoctor(int id) {
		return appointrepo.findByDoctorDoctor_id(id);
	}
	
	@Override
	public List<Patient> getPatientListByDoctor(int id) {
		
		return appointrepo.findByPatientPatient_id(id);
	}

	@Override
	public List<Appointment> getAppointmentOfThatDayOfDoctor(LocalDate date,Doctor d) {
		LocalDateTime start = date.atStartOfDay();
		LocalDateTime end = date.atTime(23,59,59);
				
		return appointrepo.getAppointmentOfThatDayOfDoctor(start,end,d);
	}
		
}
