package com.klu.service;


import java.util.List;
import com.klu.model.Appointment;
import com.klu.model.Patient;

public interface PatientService {

	String createPatient(Patient p);
	String DeletePatient(int id);
	Patient findPatientByPatientId(int id);
	
	List<Appointment> getAppointmentsList(int id);
	
}
