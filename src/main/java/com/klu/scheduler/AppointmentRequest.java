package com.klu.scheduler;
import lombok.Data;

@Data
public class AppointmentRequest {

    private String problemName;

    private int severity;
    private int age;
    private int appointmentType;

    private int patientId;
    
    
}