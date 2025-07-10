package com.hansel.FriseurPlan.core.application.usecase.hairdresser.query;

import com.hansel.FriseurPlan.core.application.adapter.hairdresser.query.HairdresserQueryClient;
import com.hansel.FriseurPlan.core.application.usecase.hairdresser.dto.GetHairdresserDto;
import com.hansel.FriseurPlan.core.application.usecase.hairdresser.dto.HairdresserReturnDto;
import com.hansel.FriseurPlan.core.domain.Address;
import com.hansel.FriseurPlan.core.domain.email.Email;
import com.hansel.FriseurPlan.core.domain.PhoneNumber;
import com.hansel.FriseurPlan.core.domain.hairdresser.Hairdresser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class HairdresserQueryUseCaseTest {

    @Mock
    private HairdresserQueryClient hairdresserQueryClient;
    @InjectMocks
    private HairdresserQueryUseCase hairdresserQueryUseCase;

    private Email email;
    private PhoneNumber phoneNumber;
    private Address address;
    private Hairdresser hairdresser;
    private HairdresserReturnDto hairdresserReturnDto;
    private GetHairdresserDto getHairdresserDto;

    @BeforeEach
    void setUp() {
        this.email = Email.create("hanelvinicius@gmail.com", true);
        this.phoneNumber = PhoneNumber.create("16992977903");
        this.address = Address.create("123 Main St", 100, "Springfield", "SP", 12345678L);
        this.hairdresser = Hairdresser.create(null, "hairdresser", new ArrayList<>(), phoneNumber, email, address);
        this.hairdresserReturnDto = HairdresserReturnDto.fromDomain(hairdresser);
        this.getHairdresserDto = new GetHairdresserDto(Pageable.unpaged(),"hairdresser");
    }
    @Test
    void getHairdresserByEmail() {
        when(hairdresserQueryClient.getHairdresserByEmail(email)).thenReturn(hairdresserReturnDto);

        Hairdresser result = hairdresserQueryUseCase.getHairdresserByEmail(email).toDomain();

        assertNotNull(result);
        assertEquals(hairdresser.getEmail(), result.getEmail());
        assertEquals(hairdresser.getName(), result.getName());
        assertEquals(hairdresser.getPhoneNumber(), result.getPhoneNumber());
        assertEquals(hairdresser.getAddress(), result.getAddress());
        assertTrue(result.getAppointments().isEmpty(), "Hairdresser should have no appointments");
        assertNull(result.getId(), "Hairdresser should not have an ID yet");

    }

    @Test
    void getHairdresserById() {
        when(hairdresserQueryClient.getHairdresserById(1L)).thenReturn(hairdresserReturnDto);

        Hairdresser result = hairdresserQueryUseCase.getHairdresserById(1L).toDomain();

        assertNotNull(result);
        assertEquals(hairdresser.getEmail(), result.getEmail());
        assertEquals(hairdresser.getName(), result.getName());
        assertEquals(hairdresser.getPhoneNumber(), result.getPhoneNumber());
        assertEquals(hairdresser.getAddress(), result.getAddress());
        assertTrue(result.getAppointments().isEmpty(), "Hairdresser should have no appointments");
        assertNull(result.getId(), "Hairdresser should not have an ID yet");
    }

    @Test
    void getHairdresser(){
        when(hairdresserQueryClient.getAllHairdressers(getHairdresserDto)).thenReturn(new PageImpl<>(List.of(hairdresserReturnDto)));

        Page<HairdresserReturnDto> result = hairdresserQueryUseCase.getAllHairdressers(getHairdresserDto);

        verify(hairdresserQueryClient).getAllHairdressers(getHairdresserDto);
        assertNotNull(result);
        assertEquals(hairdresser.getEmail(), result.getContent().getFirst().email());
        assertEquals(hairdresser.getName(), result.getContent().getFirst().name());
        assertEquals(hairdresser.getPhoneNumber().getNumber(), result.getContent().getFirst().phoneNumber());
        assertEquals(hairdresser.getAddress(), result.getContent().getFirst().address());
        assertEquals(hairdresser.getId(), result.getContent().getFirst().id());
    }

}