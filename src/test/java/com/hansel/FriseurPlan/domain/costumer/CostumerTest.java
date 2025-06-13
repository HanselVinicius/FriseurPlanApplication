package com.hansel.FriseurPlan.domain.costumer;

import com.hansel.FriseurPlan.domain.appointment.Appointment;
import com.hansel.FriseurPlan.domain.appointment.TimeRange;
import com.hansel.FriseurPlan.domain.hairdresser.Hairdresser;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CostumerTest {

    @Test
    void shouldCreateValidCostumer() {
        Costumer costumer = Costumer.create(1L, "John Doe", "123456789");

        assertNotNull(costumer);
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