package com.klu.service.serviceImplementation;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klu.model.Department;
import com.klu.model.Doctor;
import com.klu.repo.DepartmentRepo;
import com.klu.repo.DoctorRepo;
import com.klu.service.DepartmentService;

@Service
public class DepartmentImplementation implements DepartmentService {

	@Autowired
	DepartmentRepo deptRepo;
	
	@Autowired
	DoctorRepo doctorRepo;
	
	@Override
	public Department addDepartment(Department department) {
		deptRepo.save(department);
		return department;
	}

	@Override
	public String deleteDepartment(int deptId) {
		deptRepo.deleteById(deptId);
		return "Department deleted Sucessfully";
	}

	@Override
	public Department getDepartmentById(int id) {
		return deptRepo.findById(id) .orElseThrow(() -> new RuntimeException("Department not found"));
	}

	@Override
	public List<Doctor> getDoctorsListById(int id) {
		return doctorRepo.findByDepartmentDepartment_id(id);
	}

	
	
	
}
