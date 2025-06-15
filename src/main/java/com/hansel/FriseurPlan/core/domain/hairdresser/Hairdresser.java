package com.hansel.FriseurPlan.core.domain.hairdresser;

import com.hansel.FriseurPlan.core.domain.appointment.Appointment;

import java.util.List;

public class Hairdresser {
    private final Long id;
    private final String name;
    private final List<Appointment> appointments;

    private Hairdresser(Long id, String name, List<Appointment> appointments) {
        this.id = id;
        this.name = name;
        this.appointments = appointments;
    }

    public static Hairdresser create(Long id, String name, List<Appointment> appointments) {
        return new Hairdresser(id, name, appointments);
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
}
