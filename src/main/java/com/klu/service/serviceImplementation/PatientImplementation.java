package com.klu.service.serviceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klu.model.Appointment;
import com.klu.model.Patient;
import com.klu.repo.AppointmentRepo;
import com.klu.repo.PatientRepo;
import com.klu.service.PatientService;

import jakarta.transaction.Transactional;

@Service
public class PatientImplementation implements PatientService{

	@Autowired
	PatientRepo patientRepo;
	
	@Override
	public String createPatient(Patient p) {
		patientRepo.save(p);
		return "Patient created Sucessfully";
	}

	@Override
	public String DeletePatient(int id) {
		patientRepo.deleteById(id);
		return "Deleted Patient Sucessfully";
	}

	@Transactional
	@Override
	public List<Appointment> getAppointmentsList(int id) {
		
		Patient p = patientRepo.findById(id).orElseThrow(() -> new RuntimeException("Patient Not Found"));
		return p.getAppointmentList();
	}
	
}
