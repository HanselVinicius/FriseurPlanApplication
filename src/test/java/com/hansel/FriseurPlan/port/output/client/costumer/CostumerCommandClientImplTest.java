package com.hansel.FriseurPlan.port.output.client.costumer;

import com.hansel.FriseurPlan.core.domain.Email;
import com.hansel.FriseurPlan.core.domain.costumer.Costumer;
import com.hansel.FriseurPlan.port.output.entities.costumer.CostumerEntity;
import com.hansel.FriseurPlan.port.output.entities.costumer.CostumerEntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CostumerCommandClientImplTest {

    @Mock
    private CostumerEntityRepository costumerEntityRepository;

    @InjectMocks
    private CostumerCommandClientImpl costumerCommandClient;


    private Email email;

    @BeforeEach
    void setUp() {
        this.email = Email.create("hanelvinicius@gmail.com", true);
    }

    @Test
    void shouldCreateCostumer() {
        Costumer costumer = Costumer.create(null, "John Doe", "16992977903",email);
        CostumerEntity costumerEntity = CostumerMapper.toCostumerEntity(costumer);
        when(this.costumerEntityRepository.save(any(CostumerEntity.class))).thenReturn(costumerEntity);

        Costumer createdCostumer = this.costumerCommandClient.createCostumer(costumer);

        assertNotNull(createdCostumer);
        assertEquals(costumer.getName(), createdCostumer.getName());
        assertEquals(costumer.getPhoneNumber(), createdCostumer.getPhoneNumber());
        assertEquals(costumer.getId(), createdCostumer.getId());
    }

}