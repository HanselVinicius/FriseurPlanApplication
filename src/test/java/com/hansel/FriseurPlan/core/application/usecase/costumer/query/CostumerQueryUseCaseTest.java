package com.hansel.FriseurPlan.core.application.usecase.costumer.query;

import com.hansel.FriseurPlan.core.application.adapter.costumer.query.CostumerQueryClient;
import com.hansel.FriseurPlan.core.domain.Email;
import com.hansel.FriseurPlan.core.domain.costumer.Costumer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CostumerQueryUseCaseTest {

    @Mock
    private CostumerQueryClient costumerQueryClient;

    @InjectMocks
    private CostumerQueryUseCase costumerQueryUseCase;

    private Email email;
    private Costumer costumer;

    @BeforeEach
    void setUp() {
        email = Email.create("hanelvinicius@gmail.com", false);
        costumer = Costumer.create(null, "teste", "16992977903",email);
    }

    @Test
    void shouldGetCostumerByEmail() {
        when(costumerQueryClient.getCostumerByEmail(any(Email.class))).thenReturn(costumer);
        Costumer costumerByEmail = this.costumerQueryUseCase.getCostumerByEmail(email);


        verify(costumerQueryClient).getCostumerByEmail(any(Email.class));
        assertNotNull(costumerByEmail);
        assertEquals("teste", costumerByEmail.getName());
        assertEquals("16992977903", costumerByEmail.getPhoneNumber().getNumber());
    }

    @Test
    void shouldGetCostumerById() {
        when(costumerQueryClient.getCostumerById(any(Long.class))).thenReturn(costumer);
        Costumer costumerById = this.costumerQueryUseCase.getCostumerById(1L);

        verify(costumerQueryClient).getCostumerById(any(Long.class));
        assertNotNull(costumerById);
        assertEquals("teste", costumerById.getName());
        assertEquals("16992977903", costumerById.getPhoneNumber().getNumber());
    }


}