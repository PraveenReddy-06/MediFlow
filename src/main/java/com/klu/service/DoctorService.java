package com.klu.service;

import java.time.LocalDateTime;
import java.util.List;

import com.klu.model.Doctor;

public interface DoctorService {
	
	Doctor addDoctor(Doctor d);
	Doctor updateAvailability(int id,boolean available);
	boolean deleteDoctor(int id);
	Doctor getDoctorById(int id);
	List<Doctor> getAllDoctors();
	
}
