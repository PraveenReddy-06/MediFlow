package com.klu.controller;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.klu.model.Appointment;
import com.klu.model.Doctor;
import com.klu.service.AppointmentService;

@RestController
@RequestMapping("/Appointment")
public class AppointmentController {
	
	@Autowired
	AppointmentService service;
	
	@PostMapping("/add")
	public Appointment addAppointment(@RequestBody Appointment app){
		return service.addAppointment(app);
	}
	
	@GetMapping("/getAppointmentOfThatDayOfDoctor/{ldt}/{d}")
	public List<Appointment> getAppointmentOfThatDayOfDoctor(@RequestParam LocalDate ldt,@RequestParam Doctor d){
		return service.getAppointmentOfThatDayOfDoctor(ldt, d);
	}
	
	@GetMapping("/getAllAppointmentsOfDoctor/{id}")
	public List<Appointment> getAllAppointmentsOfDoctor(@RequestParam int id){
		return service.getAllAppointmentsOfDoctor(id);
	}
	
	@GetMapping("/getPatientListByDoctor/{id}")
	public List<Appointment> getPatientListByDoctor(@RequestParam int id){
		return service.getPatientListByDoctor(id);
	}
}
