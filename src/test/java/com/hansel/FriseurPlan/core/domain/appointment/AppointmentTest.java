package com.hansel.FriseurPlan.core.domain.appointment;



import com.hansel.FriseurPlan.core.domain.Address;
import com.hansel.FriseurPlan.core.domain.email.Email;
import com.hansel.FriseurPlan.core.domain.PhoneNumber;
import com.hansel.FriseurPlan.core.domain.costumer.Costumer;
import com.hansel.FriseurPlan.core.domain.hairdresser.Hairdresser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentTest {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private TimeRange timeRange;
    private Email email;
    private PhoneNumber phoneNumber;
    private Address address;

    @BeforeEach
    public void setUp() {
        this.email = Email.create("hanelvinicius@gmail.com", true);
        this.startTime =  LocalDateTime.of(2023, 10, 1, 10, 0);
        this.endTime = LocalDateTime.of(2023, 10, 1, 11, 0);
        this.timeRange = TimeRange.create(startTime, endTime);
        this.phoneNumber = PhoneNumber.create("16992977903");
        this.address = Address.create("123 Main St", 100, "Springfield", "SP", 12345678L);
    }

    @Test
    public void shouldCreateValidAppointment() {

        Costumer costumer = Costumer.create(1L, "John Doe", "16992977903",email);
        Hairdresser hairdresser = Hairdresser.create(1L, "Jane Smith", new ArrayList<>(),phoneNumber,email,address);

        Appointment appointment = Appointment.create(null,timeRange, costumer, hairdresser);

        assertNotNull(appointment);
        assertEquals(startTime, appointment.getTimeRange().getStartTime());
        assertEquals(endTime, appointment.getTimeRange().getEndTime());
    }


}