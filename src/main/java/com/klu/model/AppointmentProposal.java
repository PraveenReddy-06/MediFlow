package com.klu.model;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class AppointmentProposal {

	private Doctor doctor;
	
	private Patient patient;
	
	private LocalDateTime appointment_st_time;
	private LocalDateTime appointment_end_time;
	
	private int priorityScore;
	
	private int severity;
	private int appointmentType;
	
	
}
