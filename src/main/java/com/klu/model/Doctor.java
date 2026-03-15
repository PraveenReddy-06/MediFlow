package com.klu.model;
import lombok.*;
import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	private Integer doctorId;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="departmentId")
	private Department department;
	
	@JsonIgnore
	@OneToMany(mappedBy="doctor",cascade = CascadeType.ALL)
	List<Patient> patients;
	
	@JsonIgnore
	@OneToMany(mappedBy="doctor",cascade = CascadeType.ALL)
	List<Appointment> appointments;
	
	private String name;
	
	private LocalTime shiftStart;
	private LocalTime shiftEnd;
	private LocalTime breakStart;
	private LocalTime breakEnd;
	
	private boolean available;		
	
	public Doctor(int id,String name,Department department) {
		this.doctorId=id;
		this.name=name;
		this.department=department;
	}
}
