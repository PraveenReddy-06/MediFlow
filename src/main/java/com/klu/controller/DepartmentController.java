package com.klu.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klu.model.Department;
import com.klu.service.DepartmentService;

@RestController
@RequestMapping("/Department")
public class DepartmentController {
	
	@Autowired
	private DepartmentService service;
	
	@PostMapping("/addDepartment")
	public Department addDepartment(@RequestBody Department dept) {
		return service.addDepartment(dept);
	}
	
	@DeleteMapping("/deleteDepartment/{id}")
	public String deleteDepartment(@PathVariable int id) {
		service.deleteDepartment(id);
		return "Department Deleted Sucessfully";
	}

}
