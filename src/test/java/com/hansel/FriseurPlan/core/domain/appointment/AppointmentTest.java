package com.hansel.FriseurPlan.core.domain.appointment;



import com.hansel.FriseurPlan.core.domain.Email;
import com.hansel.FriseurPlan.core.domain.costumer.Costumer;
import com.hansel.FriseurPlan.core.domain.hairdresser.Hairdresser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentTest {
    LocalDateTime startTime = LocalDateTime.of(2023, 10, 1, 10, 0);
    LocalDateTime endTime = LocalDateTime.of(2023, 10, 1, 11, 0);
    private TimeRange timeRange;
    Email email = Email.create("hanelvinicius@gmail.com", true);

    @BeforeEach
    public void setUp() {
        this.timeRange = TimeRange.create(startTime, endTime);
    }

    @Test
    public void shouldCreateValidAppointment() {

        Costumer costumer = Costumer.create(1L, "John Doe", "16992977903",email);
        Hairdresser hairdresser = Hairdresser.create(1L, "Jane Smith", new ArrayList<>());

        Appointment appointment = Appointment.create(null,timeRange, costumer, hairdresser);

        assertNotNull(appointment);
        assertEquals(startTime, appointment.getTimeRange().getStartTime());
        assertEquals(endTime, appointment.getTimeRange().getEndTime());
    }


    @Test
    public void shouldThrowExceptionConflictingAppointment() {
        LocalDateTime startTime = LocalDateTime.of(2023, 10, 1, 10, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 10, 1, 11, 0);
        TimeRange timeRange = TimeRange.create(startTime, endTime);

        LocalDateTime appointedStartTime = LocalDateTime.of(2023, 10, 1, 10, 30);
        LocalDateTime appointedEndTime = LocalDateTime.of(2023, 10, 1, 11, 0);
        TimeRange appointedTimeRange = TimeRange.create(appointedStartTime, appointedEndTime);

        Costumer costumer = Costumer.create(1L, "John Doe", "16992977903",email);
        Hairdresser hairdresser = Hairdresser.create(1L, "Jane Smith", new ArrayList<>());

        Appointment.create(null,appointedTimeRange, costumer, hairdresser);


        assertThrows(IllegalArgumentException.class, () -> Appointment.create(null,timeRange, costumer, hairdresser));
    }


}