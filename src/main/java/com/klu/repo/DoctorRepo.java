package com.klu.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klu.model.Doctor;

@Repository
public interface DoctorRepo extends JpaRepository<Doctor,Integer>{

	/*Find Doctor where doctor.department.department_id = ?*/
	List<Doctor> findByDepartmentDepartmentId(int deptid);

}
