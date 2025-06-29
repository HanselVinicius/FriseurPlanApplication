package com.hansel.FriseurPlan.core.domain.hairdresser;

import com.hansel.FriseurPlan.core.domain.Address;
import com.hansel.FriseurPlan.core.domain.email.Email;
import com.hansel.FriseurPlan.core.domain.PhoneNumber;
import com.hansel.FriseurPlan.core.domain.appointment.Appointment;
import com.hansel.FriseurPlan.core.domain.appointment.TimeRange;
import com.hansel.FriseurPlan.core.domain.costumer.Costumer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class HairdresserTest {

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
    void shouldCreateValidHairdresser() {
        Hairdresser hairdresser = Hairdresser.create(1L, "Jane Smith", new ArrayList<>(),phoneNumber,email,address);

        assertNotNull(hairdresser);
        assertEquals(1L, hairdresser.getId());
        assertEquals("Jane Smith", hairdresser.getName());
        assertTrue(hairdresser.getAppointments().isEmpty());
    }

}