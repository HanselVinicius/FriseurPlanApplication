package com.hansel.FriseurPlan.domain;


import java.time.LocalDateTime;

public class Appointment {
    private final TimeRange timeRange;
    private final Costumer costumer;
    private final Hairdresser hairdresser;

    private Appointment(LocalDateTime startTime, LocalDateTime endTime, Costumer costumer, Hairdresser hairdresser) {
        this.timeRange = TimeRange.create(startTime, endTime);
        this.costumer = costumer;
        this.hairdresser = hairdresser;
    }

    public TimeRange getTimeRange() {
        return timeRange;
    }

    public static Appointment create(LocalDateTime startTime, LocalDateTime endTime, Costumer costumer, Hairdresser hairdresser) {
        if (costumer == null || hairdresser == null) {
            throw new IllegalArgumentException("All parameters must be provided");
        }

        Appointment appointment = new Appointment(startTime, endTime, costumer, hairdresser);
        hairdresser.addAppointment(appointment);

        return appointment;
    }


}
