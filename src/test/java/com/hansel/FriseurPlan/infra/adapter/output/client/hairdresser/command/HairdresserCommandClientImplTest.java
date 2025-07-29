package com.hansel.FriseurPlan.infra.adapter.output.client.hairdresser.command;

import com.hansel.FriseurPlan.core.domain.Address;
import com.hansel.FriseurPlan.core.domain.email.Email;
import com.hansel.FriseurPlan.core.domain.PhoneNumber;
import com.hansel.FriseurPlan.core.domain.hairdresser.Hairdresser;
import com.hansel.FriseurPlan.infra.adapter.output.entities.AddressVo;
import com.hansel.FriseurPlan.infra.adapter.output.entities.EmailVo;
import com.hansel.FriseurPlan.infra.adapter.output.entities.hairdresser.HairdresserEntity;
import com.hansel.FriseurPlan.infra.adapter.output.entities.hairdresser.HairdresserEntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HairdresserCommandClientImplTest {

    @Mock
    private HairdresserEntityRepository hairdresserEntityRepository;
    @InjectMocks
    private HairdresserCommandClientImpl hairdresserCommandClient;


    private Email email;
    private PhoneNumber phoneNumber;
    private Address address;
    private HairdresserEntity hairdresserEntity;
    private Hairdresser hairdresser;

    @BeforeEach
    void setUp() {
        this.email = Email.create("hanelvinicius@gmail.com", true);
        this.phoneNumber = PhoneNumber.create("16992977903");
        this.address = Address.create("123 Main St", 100, "Springfield", "SP", 12345678L);
        this.hairdresserEntity = new HairdresserEntity(null, "John Doe", EmailVo.fromEmailDomain(email),phoneNumber, AddressVo.fromAddressDomain(address), LocalDateTime.MAX, null,null);
        this.hairdresser = Hairdresser.create(null, "John Doe", null, phoneNumber, email, address);
    }

    @Test
    void shouldSaveHairdresser(){
        when(hairdresserEntityRepository.save(any(HairdresserEntity.class))).thenReturn(hairdresserEntity);

        Hairdresser result = hairdresserCommandClient.createHairdresser(hairdresser);
        verify(hairdresserEntityRepository).save(any(HairdresserEntity.class));
        assertNotNull(result);
        assertNotNull(result.getName(), "Hairdresser name should not be null");
        assertNotNull(result.getEmail(), "Hairdresser email should not be null");
        assertNotNull(result.getPhoneNumber(), "Hairdresser phone number should not be null");
        assertNotNull(result.getAddress(), "Hairdresser address should not be null");
        assertNotNull(result.getAppointments(), "Hairdresser appointments should not be null");

    }

}
