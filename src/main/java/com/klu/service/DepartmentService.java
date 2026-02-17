package com.klu.service;

import java.util.List;

import com.klu.model.Department;
import com.klu.model.Doctor;

public interface DepartmentService {
	
	Department addDepartment(Department department);
	Department deleteDepartment(int department);
	Department getDepartmentById(int id);
	
	
}
