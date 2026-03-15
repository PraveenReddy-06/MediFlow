package com.klu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klu.model.Appointment;
import com.klu.model.Patient;
import com.klu.service.PatientService;

@RestController
@RequestMapping("/patient")
public class PatientController {

	@Autowired
	PatientService service;
	
	@PostMapping("/createPatient")
	public String createPatient(@RequestBody Patient p) {
		service.createPatient(p);
		return "Created Patient Sucessfully";
	}
	
	@DeleteMapping("/deletePatient/{id}")
	public String DeletePatient(@PathVariable int id) {
		service.DeletePatient(id);
		return "Deleted Patient sucessfully";
	}
	
	@GetMapping("/getPatient/{id}")
	public Patient findPatient(@PathVariable int id) {
		return service.findPatientByPatientId(id);
	}
	
	@GetMapping("/getAppointments/{id}")
	public List<Appointment> getAppointmentsList(@PathVariable int id){
		return service.getAppointmentsList(id);
	}
}
