package com.klu.scheduler;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klu.model.ApplicationStatus;
import com.klu.model.Appointment;
import com.klu.model.AppointmentProposal;
import com.klu.model.AppointmentType;
import com.klu.model.Doctor;
import com.klu.model.Serverity;
import com.klu.repo.AppointmentRepo;
import com.klu.service.AppointmentService;
import com.klu.service.DepartmentService;
import com.klu.service.PatientService;

import jakarta.transaction.Transactional;

@Service
public class SchedulerEngine {
	
	@Autowired
    private AppointmentRepo appointRepo;
		
	 private final AppointmentService appointmentService;
	 private final DepartmentService departmentService;
	 private final PatientService patientService;

    public SchedulerEngine(AppointmentService appointService,DepartmentService deptService,PatientService patientService) {
        this.appointmentService=appointService;
        this.departmentService=deptService;
        this.patientService=patientService;
    }
    
    private final Map<String, AppointmentProposal> proposalRequests = new ConcurrentHashMap<>();
	public AppointmentProposal scheduleAppointment(int pid,String problemName,Serverity severity,int age,AppointmentType type) {
				
		AppointmentProposal ap = new AppointmentProposal();
	ap.setSeverity(severity);
	ap.setAppointmentType(type);
	ap.setPatient(patientService.findPatientByPatientId(pid));
		
		SymptomDetails details = SymptomMap.getProblemDetails(problemName);
		if(details==null) {
			throw new RuntimeException("No Problem found");
		}
		
		int department = details.getDepartment();
		int duration = details.getAverageConsultationTime();
	ap.setDuration(duration);
		 		 
		List<Doctor> doctorsList = departmentService.getDoctorsListById(department);
		if(doctorsList == null ||doctorsList.isEmpty()){
		    throw new RuntimeException("No doctors available for department");
		}
		
		Doctor doctor = null;
		LocalDateTime minEndTime = null;
		
		for (Doctor d : doctorsList) {
			LocalDateTime endTime = appointmentService.getLeastEndAppointmentTimeOfDoctor(d.getDoctorId());
		    if(endTime == null){
		        doctor = d;
		        minEndTime = null;
		        break;
		    }
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
		List<Appointment> appointmentsList= appointmentService.getAppointmentOfThatDayOfDoctor(date,doctor.getDoctorId());
		
		int score = PriorityScore.calPriorityScore(severity,age,duration,type);
	ap.setPriorityScore(score);
			
		int insertIndex = appointmentsList.size();	
		for (int i = 0; i < appointmentsList.size(); i++) {
		    if (score > appointmentsList.get(i).getPriorityScore()) {
		        insertIndex = i;
		        break;
		    }
		}
	ap.setInsertIndex(insertIndex);
		
		LocalDateTime predictedStartTime;
		LocalDateTime predictedEndTime;	
		final int BUFFER_MIN = 5;
		
		/* we only cal start time and add buffer for end time
		 * define where we should insert the appointment by score either at start,middle or at end 
		 if it is start get doctor shift st and assign
		 if it is middle or last get previous and add buffer
		 */
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
		/* we got date from predicted time to compare the doctorshift time and predicted time  */
		LocalDate startDate = predictedStartTime.toLocalDate();
		LocalDateTime breakStart = LocalDateTime.of(startDate, doctor.getBreakStart());
		LocalDateTime breakEnd = LocalDateTime.of(startDate, doctor.getBreakEnd());
		if (!predictedStartTime.isBefore(breakStart) && predictedStartTime.isBefore(breakEnd)) {
			    predictedStartTime = breakEnd.plusMinutes(BUFFER_MIN);
		} 
		predictedEndTime = predictedStartTime.plusMinutes(duration);
		/*handle next appointment overlap*/
		if(insertIndex < appointmentsList.size()) {

		    Appointment next = appointmentsList.get(insertIndex);

		    if(predictedEndTime.isAfter(next.getAppointment_st_time())) {

		        predictedStartTime = next.getAppointment_end_time().plusMinutes(BUFFER_MIN);
		        predictedEndTime = predictedStartTime.plusMinutes(duration);
		    }
		}
		
		/* Handle END TMIE to next date if after doctorShiftEnd*/
		LocalDateTime shiftEnd =LocalDateTime.of(predictedStartTime.toLocalDate() , doctor.getShiftEnd());
		if (predictedEndTime.isAfter(shiftEnd)) {
		    LocalDate nextDay = predictedStartTime.toLocalDate().plusDays(1);
		    predictedStartTime = LocalDateTime.of(nextDay, doctor.getShiftStart());
		    predictedEndTime =predictedStartTime.plusMinutes(duration);
		}
	ap.setAppointment_st_time(predictedStartTime);
	ap.setAppointment_end_time(predictedEndTime);
		String proposalId = UUID.randomUUID().toString();
	ap.setAppointProposalId(proposalId);
	
	proposalRequests.put(proposalId, ap);
	
	return ap;
	}
	
	@Transactional
	public Appointment ConfirmAppointment(String pid) {
		
		AppointmentProposal ap = proposalRequests.get(pid);
		
		if(ap==null) {
			throw new RuntimeException("Invalid proposal");
		}
		
		Appointment appointment = new Appointment();
		appointment.setDoctor(ap.getDoctor());
		appointment.setPatient(ap.getPatient());
		appointment.setAppointment_st_time(ap.getAppointment_st_time());
		appointment.setAppointment_end_time(ap.getAppointment_end_time());
		appointment.setPriorityScore(ap.getPriorityScore());
		appointment.setSeverity(ap.getSeverity());
		appointment.setAppointmentType(ap.getAppointmentType());
		appointment.setStatus(ApplicationStatus.Booked);
		
		
		LocalDate date = ap.getAppointment_st_time().toLocalDate();
		List<Appointment> appointmentsList= appointmentService.getAppointmentOfThatDayOfDoctor(date,ap.getDoctor().getDoctorId());
		int insertIndex=ap.getInsertIndex();
		if(insertIndex < 0 || insertIndex > appointmentsList.size()){
		    throw new RuntimeException("Invalid insert index");
		}
		appointmentService.addAppointment(appointment);
		
		final int BUFFER_MIN = 5;
		for(int i=insertIndex+1;i<appointmentsList.size();i++) {

			Appointment prevAppointment = (i == insertIndex + 1) ? appointment : appointmentsList.get(i - 1);
			Appointment curr = appointmentsList.get(i);
		
			curr.setAppointment_st_time(prevAppointment.getAppointment_end_time().plusMinutes(BUFFER_MIN));
			curr.setAppointment_end_time(curr.getAppointment_st_time().plusMinutes(curr.getDuration()));
			
			LocalDate currDate = curr.getAppointment_st_time().toLocalDate();
			LocalDateTime doctorShiftEnd = LocalDateTime.of(currDate, prevAppointment.getDoctor().getShiftEnd());
			LocalDateTime doctorBreakStart = LocalDateTime.of(currDate, prevAppointment.getDoctor().getBreakStart());
			LocalDateTime doctorBreakEnd = LocalDateTime.of(currDate, prevAppointment.getDoctor().getBreakEnd());
			
			if(curr.getAppointment_st_time().isAfter(doctorBreakStart) && curr.getAppointment_st_time().isBefore(doctorBreakEnd)) {
				curr.setAppointment_st_time(doctorBreakEnd.plusMinutes(BUFFER_MIN));
				curr.setAppointment_end_time(curr.getAppointment_st_time().plusMinutes(curr.getDuration()));
			}
			if(curr.getAppointment_end_time().isAfter(doctorShiftEnd)) {
				LocalDate nextDay = curr.getAppointment_st_time().toLocalDate().plusDays(1);
				curr.setAppointment_st_time(LocalDateTime.of(nextDay, ap.getDoctor().getShiftStart()));
				curr.setAppointment_end_time(curr.getAppointment_st_time().plusMinutes(curr.getDuration()));
			}
			appointRepo.save(curr);
		}		
		proposalRequests.remove(pid);	
		return appointment;
	}
	

}
