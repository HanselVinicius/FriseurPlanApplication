package com.hansel.FriseurPlan.core.application.usecase.hairdresser.command;

import com.hansel.FriseurPlan.core.application.adapter.HairdresserCommandClient;
import com.hansel.FriseurPlan.core.application.usecase.dto.HairdresserDto;
import com.hansel.FriseurPlan.core.domain.Address;
import com.hansel.FriseurPlan.core.domain.Email;
import com.hansel.FriseurPlan.core.domain.PhoneNumber;
import com.hansel.FriseurPlan.core.domain.hairdresser.Hairdresser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HairdresserCommandsUseCaseTest {

    @Mock
    private HairdresserCommandClient hairdresserCommandClient;
    @InjectMocks
    private HairdresserCommandsUseCase hairdresserCommandsUseCase;

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
    void shouldCreateHairdresser() {
        HairdresserDto hairdresserDto = new HairdresserDto("John Doe", phoneNumber, address);
        Hairdresser hairdresser = Hairdresser.create(null, hairdresserDto.name(), new ArrayList<>(), phoneNumber, email, address);

        when(hairdresserCommandClient.createHairdresser(any(Hairdresser.class))).thenReturn(hairdresser);
        Hairdresser result = hairdresserCommandsUseCase.createHairdresser(hairdresserDto, email);

        verify(hairdresserCommandClient).createHairdresser(any(Hairdresser.class));
        assertNotNull(result);
        assertEquals(hairdresserDto.name(), result.getName());
        assertEquals(phoneNumber, result.getPhoneNumber());
        assertEquals(email, result.getEmail());
        assertEquals(address, result.getAddress());
        assertTrue(result.getAppointments().isEmpty(), "New hairdresser should have no appointments");
        assertNull(result.getId(), "New hairdresser should not have an ID yet");
        assertEquals(hairdresserDto.address(), result.getAddress(), "Address should match the one provided in the DTO");
    }
}