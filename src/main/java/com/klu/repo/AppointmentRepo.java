package com.klu.repo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.klu.model.Appointment;
import com.klu.model.Doctor;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment,Integer>{

	@Query
	("select MIN(app.appointment_end_time) from Appointment app where app.doctor.doctorId= ?1	")
	LocalDateTime getLeastEndAppointmentTime(int id);

	  @Query("select app from Appointment app where app.doctor.doctorId = ?1")
	    List<Appointment> findByDoctorId(int id);

    @Query("select app from Appointment app where app.patient.patientId = ?1")
    List<Appointment> findByPatientId(int id);

	@Query("SELECT app FROM Appointment app WHERE app.appointment_st_time BETWEEN ?1 AND ?2 AND app.doctor = ?3 ORDER BY app.appointment_st_time")
	List<Appointment> getAppointmentOfThatDayOfDoctor(LocalDateTime start, LocalDateTime end, Doctor d);
		
}
