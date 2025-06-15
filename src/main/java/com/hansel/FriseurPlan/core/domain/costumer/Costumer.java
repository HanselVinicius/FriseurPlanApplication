package com.hansel.FriseurPlan.core.domain.costumer;

import com.hansel.FriseurPlan.core.domain.hairdresser.Hairdresser;
import com.hansel.FriseurPlan.core.domain.appointment.Appointment;
import com.hansel.FriseurPlan.core.domain.appointment.TimeRange;

public class Costumer {
    private final Long id;
    private final String name;
    private final String phoneNumber;

    private Costumer(Long id, String name, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public static Costumer create(Long id, String name, String phoneNumber) {
        if (name == null || phoneNumber == null) {
            throw new IllegalArgumentException("All parameters must be provided");
        }
        return new Costumer(id, name, phoneNumber);
    }


    public Appointment markAppointment(Hairdresser hairdresser, TimeRange timeRange) {
        return Appointment.create(timeRange, this, hairdresser);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
