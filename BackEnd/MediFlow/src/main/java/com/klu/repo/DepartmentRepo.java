package com.klu.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.klu.model.Department;

public interface DepartmentRepo extends JpaRepository<Department,Integer>{

}
