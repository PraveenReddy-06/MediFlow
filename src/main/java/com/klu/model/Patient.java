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
	private int patient_id;
	
	@ManyToOne
	@JoinColumn(name="doctor_id")
	private Doctor doctor;
	
	@OneToMany(mappedBy="patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	List<Appointment> AppointmentList;
	
	private String patient_name;
	private int age;
	
	
}
