package com.klu.model;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Getter 
@Setter 
@NoArgsConstructor
@AllArgsConstructor
@Table(name="doctors")
@Entity
public class Doctor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int doctor_id;
	
	@ManyToOne
	@JoinColumn(name="department_id")
	private Department department;
	
	@OneToMany(mappedBy="doctor",cascade = CascadeType.ALL)
	List<Patient> patients;
	
	@OneToMany(mappedBy="doctor",cascade = CascadeType.ALL)
	List<Appointment> appointments;
	
	private String name;
	private LocalDateTime shiftStart;
	private LocalDateTime shiftEnd;
	private boolean available;		
	
	public Doctor(int id,String name,Department department) {
		this.doctor_id=id;
		this.name=name;
		this.department=department;
	}
}
