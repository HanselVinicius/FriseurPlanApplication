package com.hansel.FriseurPlan.infra.port.output.client.costumer;

import com.hansel.FriseurPlan.core.domain.email.Email;
import com.hansel.FriseurPlan.core.domain.PhoneNumber;
import com.hansel.FriseurPlan.core.domain.costumer.Costumer;
import com.hansel.FriseurPlan.infra.port.output.entities.EmailVo;
import com.hansel.FriseurPlan.infra.port.output.entities.costumer.CostumerEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CostumerMapperTest {

    private Email email;
    private EmailVo emailVo;

    @BeforeEach
    void setUp() {
        this.email = Email.create("hanelvinicius@gmail.com", true);
        this.emailVo = new EmailVo(email.getEmail(), email.isVerified());
    }

    @Test
    void shouldMapCostumerToCostumerDomainEntity() {
        Costumer costumer = Costumer.create(1L, "Jane Doe", "16992977903",email);
        CostumerEntity costumerEntity = CostumerMapper.toCostumerEntity(costumer);

        assertNotNull(costumerEntity);
        assertEquals(costumer.getId(), costumerEntity.getId());
        assertEquals(costumer.getName(), costumerEntity.getName());
        assertEquals(costumer.getPhoneNumber().getNumber(), costumerEntity.getPhoneNumber().getNumber());
        assertEquals(emailVo.getEmail(), costumerEntity.getEmail().getEmail());
        assertEquals(emailVo.isVerified(), costumerEntity.getEmail().isVerified());
    }

    @Test
    void shouldMapCostumerEntityToCostumerDomain() {
        PhoneNumber phoneNumber = PhoneNumber.create("16992977903");
        CostumerEntity costumerEntity = CostumerEntity.builder()
                .id(1L)
                .name("Jane Doe")
                .phoneNumber(phoneNumber)
                .email(emailVo)
                .build();

        Costumer costumer = CostumerMapper.toCostumerDomain(costumerEntity);

        assertNotNull(costumer);
        assertEquals(costumerEntity.getId(), costumer.getId());
        assertEquals(costumerEntity.getName(), costumer.getName());
        assertEquals(costumerEntity.getPhoneNumber().getNumber(), costumer.getPhoneNumber().getNumber());
        assertEquals(emailVo.getEmail(), costumer.getEmail().getEmail());
        assertEquals(emailVo.isVerified(), costumer.getEmail().isVerified());
    }

}