package com.hansel.FriseurPlan.core.domain.hairdresser;

import com.hansel.FriseurPlan.core.domain.Address;
import com.hansel.FriseurPlan.core.domain.email.Email;
import com.hansel.FriseurPlan.core.domain.PhoneNumber;
import com.hansel.FriseurPlan.core.domain.appointment.Appointment;

import java.util.ArrayList;
import java.util.List;

public class Hairdresser {
    private final Long id;
    private final String name;
    private final List<Appointment> appointments;
    private final PhoneNumber phoneNumber;
    private final Email email;
    private final Address address;

    private Hairdresser(Long id, String name, List<Appointment> appointments,PhoneNumber phoneNumber, Email email,Address address) {
        this.id = id;
        this.name = name;
        this.appointments = appointments;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }

    public static Hairdresser create(Long id, String name, List<Appointment> appointments,PhoneNumber phoneNumber, Email email,Address address) {
        if (appointments == null) {
            appointments = new ArrayList<>();
        }
        return new Hairdresser(id, name, appointments,phoneNumber,email,address);
    }

    public void addAppointment(Appointment appointment) {
        appointments.stream().filter(a -> a.getTimeRange().overlaps(appointment.getTimeRange()))
                .findFirst()
                .ifPresent(a -> {
                    throw new IllegalArgumentException("Appointment time overlaps with an existing appointment");
                });
        this.appointments.add(appointment);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments.clear();
        if (appointments != null) {
            this.appointments.addAll(appointments);
        }
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }
}
