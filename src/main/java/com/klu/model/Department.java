package com.klu.model;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="departments")
public class Department {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int departmentId;
	
	@OneToMany(mappedBy="department", cascade = CascadeType.ALL)
	List<Doctor> doctorsList;
	
	private String departName;
	
}
