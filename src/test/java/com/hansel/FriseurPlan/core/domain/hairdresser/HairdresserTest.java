package com.hansel.FriseurPlan.core.domain.hairdresser;

import com.hansel.FriseurPlan.core.domain.appointment.Appointment;
import com.hansel.FriseurPlan.core.domain.appointment.TimeRange;
import com.hansel.FriseurPlan.core.domain.costumer.Costumer;
import com.hansel.FriseurPlan.core.domain.hairdresser.Hairdresser;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class HairdresserTest {

    @Test
    void shouldCreateValidHairdresser() {
        Hairdresser hairdresser = Hairdresser.create(1L, "Jane Smith", new ArrayList<>());

        assertNotNull(hairdresser);
        assertEquals(1L, hairdresser.getId());
        assertEquals("Jane Smith", hairdresser.getName());
        assertTrue(hairdresser.getAppointments().isEmpty());
    }

    @Test
    void shouldSuccessfullyAddAppointment() {
        Hairdresser hairdresser = Hairdresser.create(1L, "Jane Smith", new ArrayList<>());
        Appointment appointment = Appointment.create(
                TimeRange.create(LocalDateTime.of(2023, 10, 1, 10, 0), LocalDateTime.of(2023, 10, 1, 11, 0)),
                Costumer.create(1L, "John Doe", "123456789"),
                hairdresser
        );

        assertTrue(hairdresser.getAppointments().contains(appointment));
    }


}