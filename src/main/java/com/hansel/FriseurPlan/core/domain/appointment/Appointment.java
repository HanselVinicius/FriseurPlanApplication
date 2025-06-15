package com.hansel.FriseurPlan.core.domain.appointment;


import com.hansel.FriseurPlan.core.domain.costumer.Costumer;
import com.hansel.FriseurPlan.core.domain.hairdresser.Hairdresser;

public class Appointment {
    private final TimeRange timeRange;
    private final Costumer costumer;
    private final Hairdresser hairdresser;

    private Appointment(TimeRange timeRange, Costumer costumer, Hairdresser hairdresser) {
        this.timeRange = timeRange;
        this.costumer = costumer;
        this.hairdresser = hairdresser;
    }

    public TimeRange getTimeRange() {
        return timeRange;
    }

    public static Appointment create(TimeRange timeRange, Costumer costumer, Hairdresser hairdresser) {
        if (costumer == null || hairdresser == null) {
            throw new IllegalArgumentException("All parameters must be provided");
        }

        Appointment appointment = new Appointment(timeRange, costumer, hairdresser);
        hairdresser.addAppointment(appointment);

        return appointment;
    }


}
