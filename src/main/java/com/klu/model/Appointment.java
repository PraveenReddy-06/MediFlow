package com.klu.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="appointments")
public class Appointment {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int appointment_id;
	
	@ManyToOne
	@JoinColumn(name="doctor_id")
	private Doctor doctor;
	
	@ManyToOne
	@JoinColumn(name="patient_id")
	private Patient patient;
	
	private LocalDateTime appointment_st_time;
	private LocalDateTime appointment_end_time;
	
	@Enumerated(EnumType.STRING)
	private ApplicationStatus status;
	
	private int priorityScore;

	private int severity;
	private int appointmentType;
}
