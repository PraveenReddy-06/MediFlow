package com.klu.repo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.klu.model.Appointment;
import com.klu.model.Patient;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment,Integer>{

	@Query
	("select MIN(app.appointment_end_time) from Appointment app where app.doctor.doctor_id= ?1	")
	LocalDateTime getLeastEndAppointmentTime(int id);

	List<Appointment> findByDoctorDoctor_id(int id);
	
	List<Patient> findByPatientPatient_id(int id);
		
}
