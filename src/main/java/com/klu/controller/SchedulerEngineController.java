package com.klu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.klu.model.Appointment;
import com.klu.model.AppointmentProposal;
import com.klu.model.AppointmentType;
import com.klu.model.Serverity;
import com.klu.scheduler.SchedulerEngine;

@RestController
@RequestMapping("/schedule")
public class SchedulerEngineController {

	@Autowired 
	SchedulerEngine service;
	
	@GetMapping("/scheduleAppointment/{pid}/{problemName}/{severity}/{age}/{type}")
	public AppointmentProposal scheduleAppointment(@PathVariable int pid,@PathVariable String problemName,@PathVariable Serverity severity,@PathVariable int age,@PathVariable AppointmentType type) {
		return service.scheduleAppointment(pid, problemName, severity, age, type);
	}
	
	@PostMapping("/confirmAppointment")
	public Appointment ConfirmAppointment(@RequestParam String pid) {
		return service.ConfirmAppointment(pid);
	}
}
