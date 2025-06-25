package com.hansel.FriseurPlan.core.domain.appointment;


import com.hansel.FriseurPlan.core.domain.costumer.Costumer;
import com.hansel.FriseurPlan.core.domain.hairdresser.Hairdresser;

public class Appointment {
    private final Long id;
    private final TimeRange timeRange;
    private final Costumer costumer;
    private final Hairdresser hairdresser;

    private Appointment(Long id, TimeRange timeRange, Costumer costumer, Hairdresser hairdresser) {
        this.id = id;
        this.timeRange = timeRange;
        this.costumer = costumer;
        this.hairdresser = hairdresser;
    }


    public static Appointment create(Long id,TimeRange timeRange, Costumer costumer, Hairdresser hairdresser) {
        if (costumer == null || hairdresser == null) {
            throw new IllegalArgumentException("All parameters must be provided");
        }

        Appointment appointment = new Appointment(id,timeRange, costumer, hairdresser);
        hairdresser.addAppointment(appointment);

        return appointment;
    }


    public Long getId() {
        return id;
    }

    public TimeRange getTimeRange() {
        return timeRange;
    }

    public Costumer getCostumer() {
        return costumer;
    }

    public Hairdresser getHairdresser() {
        return hairdresser;
    }
}
