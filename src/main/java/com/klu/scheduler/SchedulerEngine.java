package com.klu.scheduler;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.klu.model.Appointment;
import com.klu.model.AppointmentProposal;
import com.klu.model.Doctor;
import com.klu.service.AppointmentService;
import com.klu.service.DepartmentService;
import com.klu.service.PatientService;

@Service
public class SchedulerEngine {
		
	 private final AppointmentService appointmentService;
	 private final DepartmentService departmentService;
	 private final PatientService patientService;

    public SchedulerEngine(AppointmentService appointService,DepartmentService deptService,PatientService patientService) {
        this.appointmentService=appointService;
        this.departmentService=deptService;
        this.patientService=patientService;
    }
    
	public AppointmentProposal scheduleAppointment(int pid,String problemName,int severity,int age,int type) {
				
		AppointmentProposal ap = new AppointmentProposal();
	ap.setSeverity(severity);
	ap.setAppointmentType(type);
	ap.setPatient(patientService.findPatientByPatient_id(pid));
		
		SymptomDetails details = SymptomMap.getProblemDetails(problemName);
		
		 int department = details.getDepartment();
		 int duration = details.getAverageConsultationTime();
		 		 
		List<Doctor> doctorsList = departmentService.getDoctorsListById(department);
		if(doctorsList.isEmpty()){
		    throw new RuntimeException("No doctors available for department");
		}
		
		Doctor doctor = null;
		LocalDateTime minEndTime = null;
		
		for (Doctor d : doctorsList) {
		    LocalDateTime endTime = appointmentService.getLeastEndAppointmentTimeOfDoctor(d.getDoctor_id());
		    if (minEndTime == null || endTime.isBefore(minEndTime)) {
		        doctor = d;
		        minEndTime = endTime;
		    }
		}
		LocalDate date = (minEndTime == null) ? LocalDate.now() : minEndTime.toLocalDate();
		if (doctor == null) {
		    throw new RuntimeException("No doctor available");
		}
	ap.setDoctor(doctor);
		List<Appointment> appointmentsList= appointmentService.getAppointmentOfThatDayOfDoctor(LocalDateTime.of(date, doctor.getShiftStart()),doctor);
		
		
		int score = PriorityScore.calPriorityScore(severity,age,duration);
	ap.setPriorityScore(score);
			
		int insertIndex = appointmentsList.size();	
		for (int i = 0; i < appointmentsList.size(); i++) {
		    if (score > appointmentsList.get(i).getPriorityScore()) {
		        insertIndex = i;
		        break;
		    }
		}
		
		LocalDateTime predictedStartTime;
		LocalDateTime predictedEndTime;	
		final int BUFFER_MIN = 5;
		
		/*FOR START TIME*/	
		if (appointmentsList.isEmpty()) {
		    predictedStartTime =LocalDateTime.of(date, doctor.getShiftStart());
		}
		else if (insertIndex == 0) {
		    Appointment first = appointmentsList.get(0);
		    predictedStartTime =first.getAppointment_st_time().minusMinutes(duration + BUFFER_MIN);
		    LocalDateTime shiftStart =LocalDateTime.of(first.getAppointment_st_time().toLocalDate(),doctor.getShiftStart());

		    if (predictedStartTime.isBefore(shiftStart)) {
		        predictedStartTime = shiftStart;
		    }
		}
		else {
		    Appointment prev = appointmentsList.get(insertIndex - 1);
		    predictedStartTime =prev.getAppointment_end_time().plusMinutes(BUFFER_MIN);
		}
		
		/* HANDLE BREAK TIME */
		LocalDate startDate = predictedStartTime.toLocalDate();
		LocalDateTime breakStart = LocalDateTime.of(startDate, doctor.getBreakStart());
		LocalDateTime breakEnd = LocalDateTime.of(startDate, doctor.getBreakEnd());
		if (!predictedStartTime.isBefore(breakStart) && predictedStartTime.isBefore(breakEnd)) {
			    predictedStartTime = breakEnd;
		} 
		predictedEndTime = predictedStartTime.plusMinutes(duration);
		
		/* handle end time to next date if after doctorShiftEnd*/
		LocalDateTime shiftEnd =LocalDateTime.of(predictedStartTime.toLocalDate() , doctor.getShiftEnd());
		if (predictedEndTime.isAfter(shiftEnd)) {
		    LocalDate nextDay = predictedStartTime.toLocalDate().plusDays(1);
		    predictedStartTime = LocalDateTime.of(nextDay, doctor.getShiftStart());
		    predictedEndTime =predictedStartTime.plusMinutes(duration);
		}
	ap.setAppointment_st_time(predictedStartTime);
	ap.setAppointment_end_time(predictedEndTime);	
	return ap;
	}

}
