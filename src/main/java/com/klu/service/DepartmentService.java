package com.klu.service;

import java.util.List;
import com.klu.model.Department;
import com.klu.model.Doctor;

public interface DepartmentService {
	
	Department addDepartment(Department department);
	String deleteDepartment(int id);
	
	Department getDepartmentById(int id);
	List<Doctor> getDoctorsListById(int id);
	
}
