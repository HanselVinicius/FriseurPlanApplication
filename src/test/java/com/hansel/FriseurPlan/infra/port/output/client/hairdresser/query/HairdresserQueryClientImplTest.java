package com.hansel.FriseurPlan.infra.port.output.client.hairdresser.query;

import com.hansel.FriseurPlan.core.application.usecase.hairdresser.dto.GetHairdresserDto;
import com.hansel.FriseurPlan.core.application.usecase.hairdresser.dto.HairdresserReturnDto;
import com.hansel.FriseurPlan.core.domain.Address;
import com.hansel.FriseurPlan.core.domain.PhoneNumber;
import com.hansel.FriseurPlan.core.domain.email.Email;
import com.hansel.FriseurPlan.core.domain.hairdresser.Hairdresser;
import com.hansel.FriseurPlan.infra.port.output.client.hairdresser.HairdresserMapper;
import com.hansel.FriseurPlan.infra.port.output.client.hairdresser.specifications.GetHairdresserByParamsSpec;
import com.hansel.FriseurPlan.infra.port.output.entities.hairdresser.HairdresserEntity;
import com.hansel.FriseurPlan.infra.port.output.entities.hairdresser.HairdresserEntityRepository;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HairdresserQueryClientImplTest {

    @Mock
    private HairdresserEntityRepository hairdresserEntityRepository;
    @InjectMocks
    private HairdresserQueryClientImpl hairdresserQueryClient;

    private Email email;
    private PhoneNumber phoneNumber;
    private Address address;
    private Hairdresser hairdresser;
    private HairdresserEntity hairdresserEntity;
    private GetHairdresserDto getHairdresserDto;

    @BeforeEach
    void setUp() {
        this.email = Email.create("hanelvinicius@gmail.com", true);
        this.phoneNumber = PhoneNumber.create("16992977903");
        this.address = Address.create("123 Main St", 100, "Springfield", "SP", 12345678L);
        this.hairdresser = Hairdresser.create(null, "hairdresser", new ArrayList<>(), phoneNumber, email, address);
        this.hairdresserEntity = HairdresserMapper.toEntity(hairdresser);
        this.getHairdresserDto = new GetHairdresserDto(Pageable.unpaged(),"hairdresser");
    }


    @Test
    void getHairdresserByEmail() {
        when(hairdresserEntityRepository.getHairdresserEntityByEmail(email.getEmail())).thenReturn(Optional.of(hairdresserEntity));

        HairdresserReturnDto result = hairdresserQueryClient.getHairdresserByEmail(email);

        assertNotNull(result);
        assertEquals(hairdresser.getEmail().getEmail(), result.email().getEmail());
        assertEquals(hairdresser.getName(), result.name());
        assertEquals(hairdresser.getPhoneNumber().getNumber(), result.phoneNumber());
        assertEquals(hairdresser.getAddress(), result.address());
    }

    @Test
    void getHairdresserById() {
        when(hairdresserEntityRepository.findById(1L)).thenReturn(Optional.of(hairdresserEntity));

        HairdresserReturnDto result = hairdresserQueryClient.getHairdresserById(1L);

        assertNotNull(result);
        assertEquals(hairdresser.getEmail().getEmail(), result.email().getEmail());
        assertEquals(hairdresser.getName(), result.name());
        assertEquals(hairdresser.getPhoneNumber().getNumber(), result.phoneNumber());
        assertEquals(hairdresser.getAddress(), result.address());
        assertNull(result.id(), "Hairdresser should not have an ID yet");
    }


    @Test
    void getHairdresser(){
        PageImpl<HairdresserEntity> page = new PageImpl<>(List.of(this.hairdresserEntity));
        when(hairdresserEntityRepository.findAll(any(GetHairdresserByParamsSpec.class),any(Pageable.class))).thenReturn(page);

        Page<HairdresserReturnDto> result = hairdresserQueryClient.getAllHairdressers(getHairdresserDto);

        verify(this.hairdresserEntityRepository).findAll(any(GetHairdresserByParamsSpec.class),any(Pageable.class));
        assertNotNull(result);
        assertEquals(hairdresser.getEmail(), result.getContent().getFirst().email());
        assertEquals(hairdresser.getName(), result.getContent().getFirst().name());
        assertEquals(hairdresser.getPhoneNumber().getNumber(), result.getContent().getFirst().phoneNumber());
        assertEquals(hairdresser.getAddress(), result.getContent().getFirst().address());
        assertEquals(hairdresser.getId(), result.getContent().getFirst().id());
    }


}