package com.klu.model;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="patients")
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int patientId;
	
	@ManyToOne
	@JoinColumn(name="doctorId")
	private Doctor doctor;
	
	@OneToMany(mappedBy="patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	List<Appointment> AppointmentList;
	
	private String patient_name;
	private int age;
	
	
}
