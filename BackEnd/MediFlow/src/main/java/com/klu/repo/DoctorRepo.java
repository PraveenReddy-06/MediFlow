package com.klu.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.klu.model.Doctor;

public interface DoctorRepo extends JpaRepository<Doctor,Integer>{

}
