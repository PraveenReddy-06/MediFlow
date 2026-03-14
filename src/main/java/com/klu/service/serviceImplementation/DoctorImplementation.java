package com.klu.service.serviceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.klu.model.Doctor;
import com.klu.model.Patient;
import com.klu.repo.DoctorRepo;
import com.klu.repo.PatientRepo;
import com.klu.service.DoctorService;


@Service
public class DoctorImplementation implements DoctorService{

	@Autowired
	DoctorRepo doctorRepo;
	
	@Autowired
	PatientRepo patientRepo;
	
	@Override
	public Doctor addDoctor(Doctor d) {
		doctorRepo.save(d);
		return d;
	}

	@Transactional
	@Override
	public String updateAvailability(int id, boolean available) {
		Doctor d = doctorRepo.findById(id).orElseThrow(() -> new RuntimeException("Doctor Not Found"));
		d.setAvailable(available);

		return "Updated Availability Of Doctor";
	}

	@Override
	public String deleteDoctor(int id) {
		doctorRepo.deleteById(id);
		return "Doctor Deleted Sucessfull";
	}

	@Override
	public Doctor getDoctorById(int id) {
		return doctorRepo.findById(id).orElseThrow(() -> new RuntimeException("Doctor Not Found"));
	}

	@Override
	public List<Patient> getAllPatients(int id) {	
		return patientRepo.findByDoctorDoctorId(id);
	}
	
}
