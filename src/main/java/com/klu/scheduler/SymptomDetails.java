package com.klu.scheduler;

public class SymptomDetails {

    private int department;
    private int averageConsultationTime;

    public SymptomDetails(int department, int avgTime) {
        this.department = department;
        this.averageConsultationTime = avgTime;
    }

    public int getDepartment() {
        return department;
    }

    public int getAverageConsultationTime() {
        return averageConsultationTime;
    }
}
