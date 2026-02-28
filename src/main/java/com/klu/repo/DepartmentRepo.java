package com.klu.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klu.model.Department;

@Repository
public interface DepartmentRepo extends JpaRepository<Department,Integer>{

	
}
