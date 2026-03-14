package com.klu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.klu.model.Doctor;
import com.klu.model.Patient;
import com.klu.service.DoctorService;

@RestController
@RequestMapping("/Doctor")
public class DoctorController {

	@Autowired
	DoctorService service;
	
	@PostMapping("/addDoctor")
	public Doctor addDoctor(@PathVariable Doctor d) {
		return service.addDoctor(d);
	}
	
	@PostMapping("/updateAvailability")
	public String updateAvailability(@PathVariable int id,@PathVariable boolean available) {
		return service.updateAvailability(id, available);
	}
	
	@GetMapping("/getDoctor")
	public Doctor getDoctorById(@PathVariable int id) {
		return service.getDoctorById(id);
	}
	
	@GetMapping("/getPatients")
	public List<Patient> getAllPatients(@RequestParam int id){
		return service.getAllPatients(id);
	}
}
