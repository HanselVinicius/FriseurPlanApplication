package com.hansel.FriseurPlan.core.domain.costumer;

import com.hansel.FriseurPlan.core.domain.appointment.Appointment;
import com.hansel.FriseurPlan.core.domain.appointment.TimeRange;
import com.hansel.FriseurPlan.core.domain.costumer.Costumer;
import com.hansel.FriseurPlan.core.domain.hairdresser.Hairdresser;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CostumerTest {

    @Test
    void shouldCreateValidCostumer() {
        Costumer costumer = Costumer.create(null, "John Doe", "123456789");

        assertNotNull(costumer);
    }

    @Test
    void shouldThrowExceptionWhenCreatingCostumerWithNullName() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> Costumer.create(1L, null, "123456789"));

        assertEquals("All parameters must be provided", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenCreatingCostumerWithNullPhoneNumber() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> Costumer.create(1L, "John Doe", null));

        assertEquals("All parameters must be provided", exception.getMessage());
    }

    @Test
    void shouldMarkAppointment() {
        Costumer costumer = Costumer.create(1L, "John Doe", "123456789");
        Hairdresser hairdresser = Hairdresser.create(1L, "Jane Smith",new ArrayList<>());
        TimeRange timeRange = TimeRange.create(LocalDateTime.of(2023, 10, 1, 10, 0), LocalDateTime.of(2023, 10, 1, 11, 0));

        Appointment appointment = costumer.markAppointment(hairdresser, timeRange);

        assertNotNull(appointment);
        assertEquals(timeRange, appointment.getTimeRange());
    }

}