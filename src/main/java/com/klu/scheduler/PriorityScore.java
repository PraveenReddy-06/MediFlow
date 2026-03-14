package com.klu.scheduler;

import com.klu.model.Serverity;
import com.klu.model.AppointmentType;

public class PriorityScore {

    public static int calPriorityScore(Serverity severity,int age,int duration, AppointmentType appointmentType) {

        int severityScore = 0;
        switch (severity) {
            case Critical:
                severityScore = 60; break;
            case High:
                severityScore = 45; break;
            case Moderate:
                severityScore = 30; break;
            case Low:
                severityScore = 15; break;
        }

        int appointmentScore = 0;
        switch (appointmentType) {
            case Emergency:
                appointmentScore = 25;
                break;
            case NewConsultation:
                appointmentScore = 15;
                break;
            case FollowUp:
                appointmentScore = 8;
                break;
            case RoutineCheckUp:
                appointmentScore = 3;
                break;
        }

        int ageScore = getAgeScore(age);
        int durationScore = getDurationScore(duration);

        int bonus = 0;
        if (severity == Serverity.Critical && appointmentType == AppointmentType.Emergency) {
            bonus = 20;
        }
        return severityScore + appointmentScore + ageScore + durationScore + bonus;
    }


    private static int getAgeScore(int age) {
        if (age >= 75) return 18;
        if (age >= 60) return 14;
        if (age < 5) return 14;
        if (age >= 40) return 8;
        if (age >= 18) return 5;
        return 3;
    }


    private static int getDurationScore(int duration) {

        if (duration <= 10) return 6;
        if (duration <= 15) return 4;
        if (duration <= 20) return 2;
        return 0;
    }
}