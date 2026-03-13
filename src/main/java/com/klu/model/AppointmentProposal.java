package com.klu.model;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class AppointmentProposal {

	private String appointProposalId;
	private Doctor doctor;
	
	private Patient patient;
	
	private LocalDateTime appointment_st_time;
	private LocalDateTime appointment_end_time;
	
	private int priorityScore;
	private int insertIndex;
	
	private int severity;
	private int appointmentType;
	private int duration;
	
	
}
