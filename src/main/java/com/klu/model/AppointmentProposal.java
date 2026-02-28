package com.klu.model;

import java.time.LocalDateTime;

public class AppointmentProposal {

	private int doctorId;
	private String doctorName;
	
	private LocalDateTime proposedStartTime;
    private LocalDateTime proposedEndTime;

    private int predictedTime;
}
