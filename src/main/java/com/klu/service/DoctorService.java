package com.klu.service;

import java.util.List;
import com.klu.model.Doctor;
import com.klu.model.Patient;

public interface DoctorService {
	
	Doctor addDoctor(Doctor d);
	String updateAvailability(int id,boolean available);
	String deleteDoctor(int id);
	
	Doctor getDoctorById(int id);
	List<Patient> getAllPatients(int id);
	
}
