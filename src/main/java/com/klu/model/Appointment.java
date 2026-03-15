package com.klu.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	private Integer appointmentId;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="doctorId")
	private Doctor doctor;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="patient_id")
	private Patient patient;
	
	private LocalDateTime appointment_st_time;
	private LocalDateTime appointment_end_time;
	
	@Enumerated(EnumType.STRING)
	private ApplicationStatus status;
	
	private int priorityScore;

	@Enumerated(EnumType.STRING)
	private Serverity severity;
	
	@Enumerated(EnumType.STRING)
	private AppointmentType appointmentType;
	private int duration;
}
