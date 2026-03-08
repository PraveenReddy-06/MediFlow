package com.klu.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.klu.model.Appointment;
import com.klu.model.Patient;
@Service
public interface PatientService {

	String createPatient(Patient p);
	String DeletePatient(int id);
	Patient findPatientByPatient_id(int id);
	
	List<Appointment> getAppointmentsList(int id);
	
}
