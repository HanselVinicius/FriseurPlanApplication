package com.hansel.FriseurPlan.domain;


import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentTest {

    @Test
    public void shouldCreateValidAppointment() {
        LocalDateTime startTime = LocalDateTime.of(2023, 10, 1, 10, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 10, 1, 11, 0);
        Costumer costumer = new Costumer(1L, "John Doe", "123456789");
        Hairdresser hairdresser = new Hairdresser(1L, "Jane Smith", new ArrayList<>());

        Appointment appointment = Appointment.create(startTime, endTime, costumer, hairdresser);

        assertNotNull(appointment);
        assertEquals(startTime, appointment.getTimeRange().getStartTime());
        assertEquals(endTime, appointment.getTimeRange().getEndTime());
    }

    @Test
    public void shouldThrowExceptionForNullParameters() {
        LocalDateTime startTime = LocalDateTime.of(2023, 10, 1, 10, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 10, 1, 11, 0);
        Costumer costumer = new Costumer(1L, "John Doe", "123456789");
        Hairdresser hairdresser = new Hairdresser(1L, "Jane Smith", List.of());

        assertThrows(IllegalArgumentException.class, () -> Appointment.create(null, endTime, costumer, hairdresser));
        assertThrows(IllegalArgumentException.class, () -> Appointment.create(startTime, null, costumer, hairdresser));
        assertThrows(IllegalArgumentException.class, () -> Appointment.create(startTime, endTime, null, hairdresser));
        assertThrows(IllegalArgumentException.class, () -> Appointment.create(startTime, endTime, costumer, null));
    }

    @Test
    public void shouldThrowExceptionForStartTimeAfterEndTime() {
        LocalDateTime startTime = LocalDateTime.of(2023, 10, 1, 11, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 10, 1, 10, 0);
        Costumer costumer = new Costumer(1L, "John Doe", "123456789");
        Hairdresser hairdresser = new Hairdresser(1L, "Jane Smith", List.of());

        assertThrows(IllegalArgumentException.class, () -> Appointment.create(startTime, endTime, costumer, hairdresser));
    }

    @Test
    public void shouldThrowExceptionConflictingAppointment() {
        LocalDateTime startTime = LocalDateTime.of(2023, 10, 1, 10, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 10, 1, 11, 0);

        LocalDateTime appointedStartTime = LocalDateTime.of(2023, 10, 1, 10, 30);
        LocalDateTime appointedEndTime = LocalDateTime.of(2023, 10, 1, 11, 0);

        Costumer costumer = new Costumer(1L, "John Doe", "123456789");
        Hairdresser hairdresser = new Hairdresser(1L, "Jane Smith", new ArrayList<>());

        Appointment.create(appointedStartTime, appointedEndTime, costumer, hairdresser);


        assertThrows(IllegalArgumentException.class, () -> Appointment.create(startTime, endTime, costumer, hairdresser));
    }


}