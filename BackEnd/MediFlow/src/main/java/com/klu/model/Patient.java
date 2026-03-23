package com.klu.model;

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

	@ManyToOne
	@JoinColumn(name="doctor_id")
	private Doctor doctor;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int patient_id;
	
	private String patient_name;
	private int age;
	
}
