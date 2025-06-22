package com.hansel.FriseurPlan.infra.port.output.client.costumer.query;

import com.hansel.FriseurPlan.core.domain.email.Email;
import com.hansel.FriseurPlan.core.domain.costumer.Costumer;
import com.hansel.FriseurPlan.infra.port.output.client.costumer.CostumerMapper;
import com.hansel.FriseurPlan.infra.port.output.entities.costumer.CostumerEntity;
import com.hansel.FriseurPlan.infra.port.output.entities.costumer.CostumerEntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CostumerQueryClientImplTest {

    @Mock
    private CostumerEntityRepository costumerEntityRepository;
    @InjectMocks
    private CostumerQueryClientImpl costumerQueryClient;

    private Email email;
    private Costumer costumer;
    private CostumerEntity costumerEntity;

    @BeforeEach
    void setUp() {
        email = Email.create("hanelvinicius@gmail.com", false);
        costumer = Costumer.create(null, "teste", "16992977903",email);
        costumerEntity = CostumerMapper.toCostumerEntity(costumer);
    }

    @Test
    void getCostumerByEmail() {
        when(costumerEntityRepository.getCostumerEntityByEmail(any(String.class))).thenReturn(costumerEntity);
        Costumer costumerByEmail = costumerQueryClient.getCostumerByEmail(email);

        verify(costumerEntityRepository).getCostumerEntityByEmail(any(String.class));
        assertNotNull(costumerByEmail);
        assertEquals("teste", costumerByEmail.getName());
        assertEquals("16992977903", costumerByEmail.getPhoneNumber().getNumber());
        assertEquals(email.getEmail(), costumerByEmail.getEmail().getEmail());
        assertNull(costumerByEmail.getId());
    }


    @Test
    void getCostumerById() {
        when(costumerEntityRepository.findById(any(Long.class))).thenReturn(Optional.of(costumerEntity));

        Costumer costumerByEmail = costumerQueryClient.getCostumerById(1L);

        verify(costumerEntityRepository).findById(any(Long.class));
        assertNotNull(costumerByEmail);
        assertEquals("teste", costumerByEmail.getName());
        assertEquals("16992977903", costumerByEmail.getPhoneNumber().getNumber());
        assertEquals(email.getEmail(), costumerByEmail.getEmail().getEmail());
        assertNull(costumerByEmail.getId());
    }

}