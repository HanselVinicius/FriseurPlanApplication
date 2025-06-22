package com.hansel.FriseurPlan.core.application.usecase.hairdresser.command;

import com.hansel.FriseurPlan.core.application.adapter.hairdresser.command.HairdresserCommandClient;
import com.hansel.FriseurPlan.core.application.usecase.dto.HairdresserDto;
import com.hansel.FriseurPlan.core.application.usecase.hairdresser.query.HairdresserQueryUseCase;
import com.hansel.FriseurPlan.core.domain.Address;
import com.hansel.FriseurPlan.core.domain.email.Email;
import com.hansel.FriseurPlan.core.domain.PhoneNumber;
import com.hansel.FriseurPlan.core.domain.email.ValidateEmailIsUniqueService;
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
class HairdresserCommandUseCaseTest {

    @Mock
    private HairdresserCommandClient hairdresserCommandClient;
    @Mock
    private ValidateEmailIsUniqueService validateEmailIsUniqueService;
    @InjectMocks
    private HairdresserCommandUseCase hairdresserCommandUseCase;

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
        when(validateEmailIsUniqueService.validate(hairdresser.getEmail())).thenReturn(true);

        Hairdresser result = hairdresserCommandUseCase.createHairdresser(hairdresserDto, email);

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

    @Test
    void shouldFailToCreateHairdresserWhenEmailAlreadyExists() {
        HairdresserDto hairdresserDto = new HairdresserDto("John Doe", phoneNumber, address);
        Hairdresser hairdresser = Hairdresser.create(null, hairdresserDto.name(), new ArrayList<>(), phoneNumber, email, address);

        when(validateEmailIsUniqueService.validate(hairdresser.getEmail())).thenReturn(false);

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
            hairdresserCommandUseCase.createHairdresser(hairdresserDto, email)
        );

        assertEquals("Specified email is already in use.", exception.getMessage(), "Should throw an exception when trying to create a hairdresser with an existing email");
    }
}