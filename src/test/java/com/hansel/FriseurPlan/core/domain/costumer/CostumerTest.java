package com.hansel.FriseurPlan.core.domain.costumer;

import com.hansel.FriseurPlan.core.domain.Address;
import com.hansel.FriseurPlan.core.domain.email.Email;
import com.hansel.FriseurPlan.core.domain.PhoneNumber;
import com.hansel.FriseurPlan.core.domain.appointment.Appointment;
import com.hansel.FriseurPlan.core.domain.appointment.TimeRange;
import com.hansel.FriseurPlan.core.domain.hairdresser.Hairdresser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CostumerTest {

    private Email email;
    private PhoneNumber phoneNumber;
    private Address address;


    @BeforeEach
    void setUp() {
        this.email = Email.create("hanelvinicius@gmail.com", true);
        this.phoneNumber = PhoneNumber.create("16992977903");
        this.address = Address.create("123 Main St", 100, "Springfield", "SP", 12345678L);
    }

    @Test
    void shouldCreateValidCostumer() {
        Costumer costumer = Costumer.create(null, "John Doe", "16992977903",email);

        assertNotNull(costumer);
    }

    @Test
    void shouldThrowExceptionWhenCreatingCostumerWithNullName() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> Costumer.create(1L, null, "16992977903",email));

        assertEquals("All parameters must be provided", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenCreatingCostumerWithNullPhoneNumber() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> Costumer.create(1L, "John Doe", null,email));

        assertEquals("All parameters must be provided", exception.getMessage());
    }

    @Test
    void shouldMarkAppointment() {
        Costumer costumer = Costumer.create(1L, "John Doe", "16992977903",email);
        Hairdresser hairdresser = Hairdresser.create(1L, "Jane Smith",new ArrayList<>(),phoneNumber,email,address);
        TimeRange timeRange = TimeRange.create(LocalDateTime.of(2023, 10, 1, 10, 0), LocalDateTime.of(2023, 10, 1, 11, 0));

        Appointment appointment = costumer.markAppointment(hairdresser, timeRange);

        assertNotNull(appointment);
        assertEquals(timeRange, appointment.getTimeRange());
    }

}