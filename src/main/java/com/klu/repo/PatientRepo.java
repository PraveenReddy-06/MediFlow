package com.klu.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.klu.model.Patient;

@Repository
public interface PatientRepo extends JpaRepository<Patient,Integer>{

	List<Patient> findByDoctorDoctor_id(int id);

}
