package com.hansel.FriseurPlan.port.output.client.costumer;

import com.hansel.FriseurPlan.core.domain.PhoneNumber;
import com.hansel.FriseurPlan.core.domain.costumer.Costumer;
import com.hansel.FriseurPlan.port.output.entities.costumer.CostumerEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CostumerMapperTest {

    @Test
    void shouldMapCostumerToCostumerEntity() {
        Costumer costumer = Costumer.create(1L, "Jane Doe", "16992977903");
        CostumerEntity costumerEntity = CostumerMapper.toCostumerEntity(costumer);

        assertNotNull(costumerEntity);
        assertEquals(costumer.getId(), costumerEntity.getId());
        assertEquals(costumer.getName(), costumerEntity.getName());
        assertEquals(costumer.getPhoneNumber().getNumber(), costumerEntity.getPhoneNumber().getNumber());
    }

    @Test
    void shouldMapCostumerEntityToCostumer() {
        PhoneNumber phoneNumber = PhoneNumber.create("16992977903");
        CostumerEntity costumerEntity = CostumerEntity.builder()
                .id(1L)
                .name("Jane Doe")
                .phoneNumber(phoneNumber)
                .build();

        Costumer costumer = CostumerMapper.toCostumer(costumerEntity);

        assertNotNull(costumer);
        assertEquals(costumerEntity.getId(), costumer.getId());
        assertEquals(costumerEntity.getName(), costumer.getName());
        assertEquals(costumerEntity.getPhoneNumber().getNumber(), costumer.getPhoneNumber().getNumber());
    }

}