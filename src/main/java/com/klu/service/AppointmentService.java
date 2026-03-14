package com.klu.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import com.klu.model.Appointment;
import com.klu.model.Doctor;

public interface AppointmentService {

	Appointment addAppointment(Appointment app);
	
	/* i want to return doctor with doctor whose least end-time is min among all the doctors*/
	LocalDateTime getLeastEndAppointmentTimeOfDoctor(int id);
	List<Appointment> getAppointmentOfThatDayOfDoctor(LocalDate ldt,Doctor d);
	
	List<Appointment> getAllAppointmentsOfDoctor(int id);
	
	List<Appointment> getPatientListByDoctor(int id);
	 
}
/* int m=integer.max;
 * doctor doc = new doctor();
 * for(docor d:doctorslist){
 *
 * 	 if(d.endtime<min){
 *     doc=d;
 * 	}
 * }
 * 
 * list<Patients> = from that doctor
 */
