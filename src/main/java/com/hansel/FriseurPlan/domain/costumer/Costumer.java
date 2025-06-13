package com.hansel.FriseurPlan.domain.costumer;

import com.hansel.FriseurPlan.domain.hairdresser.Hairdresser;
import com.hansel.FriseurPlan.domain.appointment.Appointment;
import com.hansel.FriseurPlan.domain.appointment.TimeRange;

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
        return new Costumer(id, name, phoneNumber);
    }


    public Appointment markAppointment(Hairdresser hairdresser, TimeRange timeRange) {
        return Appointment.create(timeRange, this, hairdresser);
    }

}
