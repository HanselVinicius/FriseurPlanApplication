package com.hansel.FriseurPlan.domain;

import java.util.List;

public class Hairdresser {
    private final Long id;
    private final String name;
    private final List<Appointment> appointments;

    public Hairdresser(Long id, String name, List<Appointment> appointments) {
        this.id = id;
        this.name = name;
        this.appointments = appointments;
    }

    public void addAppointment(Appointment appointment) {
        appointments.stream().filter(a -> a.getTimeRange().overlaps(appointment.getTimeRange()))
                .findFirst()
                .ifPresent(a -> {
                    throw new IllegalArgumentException("Appointment time overlaps with an existing appointment");
                });
        this.appointments.add(appointment);
    }
}
