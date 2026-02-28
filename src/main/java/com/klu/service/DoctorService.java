package com.klu.service;

import java.time.LocalDateTime; 
import java.util.List;

import org.springframework.stereotype.Service;

import com.klu.model.Doctor;
import com.klu.model.Patient;

public interface DoctorService {
	
	Doctor addDoctor(Doctor d);
	String updateAvailability(int id,boolean available);
	String deleteDoctor(int id);
	
	Doctor getDoctorById(int id);
	List<Patient> getAllPatients(int id);
	
}
