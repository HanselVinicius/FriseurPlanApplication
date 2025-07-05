package com.hansel.FriseurPlan.core.domain.costumer;

import com.hansel.FriseurPlan.core.domain.PhoneNumber;
import com.hansel.FriseurPlan.core.domain.appointment.Appointment;
import com.hansel.FriseurPlan.core.domain.appointment.TimeRange;
import com.hansel.FriseurPlan.core.domain.email.Email;
import com.hansel.FriseurPlan.core.domain.hairdresser.Hairdresser;

public class Costumer {
    private final Long id;
    private final String name;
    private final PhoneNumber phoneNumber;
    private final Email email;

    private Costumer(Long id, String name, String phoneNumber, Email email) {
        this.id = id;
        this.name = name;
        this.phoneNumber = PhoneNumber.create(phoneNumber);
        this.email = email;
    }

    public static Costumer create(Long id, String name, String phoneNumber, Email email) {
        if (name == null || phoneNumber == null) {
            throw new IllegalArgumentException("All parameters must be provided");
        }
        return new Costumer(id, name, phoneNumber,email);
    }


    public Appointment markAppointment(Hairdresser hairdresser, TimeRange timeRange) {
        return Appointment.create(null,timeRange, this, hairdresser);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public Email getEmail() {
        return email;
    }
}
