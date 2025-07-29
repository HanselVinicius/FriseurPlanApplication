package com.hansel.FriseurPlan.infra.adapter.output.client.hairdresser;

import com.hansel.FriseurPlan.core.domain.Address;
import com.hansel.FriseurPlan.core.domain.email.Email;
import com.hansel.FriseurPlan.core.domain.PhoneNumber;
import com.hansel.FriseurPlan.core.domain.hairdresser.Hairdresser;
import com.hansel.FriseurPlan.infra.adapter.output.entities.AddressVo;
import com.hansel.FriseurPlan.infra.adapter.output.entities.EmailVo;
import com.hansel.FriseurPlan.infra.adapter.output.entities.hairdresser.HairdresserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class HairdresserMapperTest {

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
    void shouldMapHairdresserToEntity() {

        HairdresserEntity entity = HairdresserMapper.toEntity(hairdresser);

        assertNotNull(entity);
        assertEquals(hairdresser.getName(), entity.getName());
        assertEquals(hairdresser.getEmail().getEmail(), entity.getEmail().getEmail());
        assertEquals(hairdresser.getPhoneNumber().getNumber(), entity.getPhoneNumber().getNumber());
        assertEquals(hairdresser.getAddress().getStreet(), entity.getAddress().getStreet());
        assertEquals(hairdresser.getAddress().getCity(), entity.getAddress().getCity());
        assertEquals(hairdresser.getAddress().getState(), entity.getAddress().getState());
        assertEquals(hairdresser.getAddress().getZipCode(), entity.getAddress().getZipCode());
    }

    @Test
    void shouldMapEntityToHairdresser() {

        Hairdresser hairdresserFromEntity = HairdresserMapper.toDomain(hairdresserEntity);

        assertNotNull(hairdresserFromEntity);
        assertEquals(hairdresserEntity.getName(), hairdresserFromEntity.getName());
        assertEquals(hairdresserEntity.getEmail().toEmailDomain().getEmail(), hairdresserFromEntity.getEmail().getEmail());
        assertEquals(hairdresserEntity.getPhoneNumber().getNumber(), hairdresserFromEntity.getPhoneNumber().getNumber());
        assertEquals(hairdresserEntity.getAddress().toAddressDomain().getStreet(), hairdresserFromEntity.getAddress().getStreet());
        assertEquals(hairdresserEntity.getAddress().toAddressDomain().getCity(), hairdresserFromEntity.getAddress().getCity());
        assertEquals(hairdresserEntity.getAddress().toAddressDomain().getState(), hairdresserFromEntity.getAddress().getState());
        assertEquals(hairdresserEntity.getAddress().toAddressDomain().getZipCode(), hairdresserFromEntity.getAddress().getZipCode());
    }

}