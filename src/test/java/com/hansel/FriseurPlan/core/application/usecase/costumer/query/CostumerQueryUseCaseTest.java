package com.hansel.FriseurPlan.core.application.usecase.costumer.query;

import com.hansel.FriseurPlan.core.application.port.costumer.query.CostumerQueryClient;
import com.hansel.FriseurPlan.core.application.usecase.costumer.dto.CostumerReturnDto;
import com.hansel.FriseurPlan.core.domain.email.Email;
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
    private CostumerReturnDto costumerReturnDto;

    @BeforeEach
    void setUp() {
        email = Email.create("hanelvinicius@gmail.com", false);
        costumer = Costumer.create(null, "teste", "16992977903",email);
        costumerReturnDto = CostumerReturnDto.fromDomain(costumer);
    }

    @Test
    void shouldGetCostumerByEmail() {
        when(costumerQueryClient.getCostumerByEmail(any(Email.class))).thenReturn(costumerReturnDto);
        CostumerReturnDto costumerByEmail = this.costumerQueryUseCase.getCostumerByEmail(email);


        verify(costumerQueryClient).getCostumerByEmail(any(Email.class));
        assertNotNull(costumerByEmail);
        assertEquals("teste", costumerByEmail.name());
        assertEquals("16992977903", costumerByEmail.phoneNumber().getNumber());
    }

    @Test
    void shouldGetCostumerById() {
        when(costumerQueryClient.getCostumerById(any(Long.class))).thenReturn(costumerReturnDto);
        CostumerReturnDto costumerById = this.costumerQueryUseCase.getCostumerById(1L);

        verify(costumerQueryClient).getCostumerById(any(Long.class));
        assertNotNull(costumerById);
        assertEquals("teste", costumerById.name());
        assertEquals("16992977903", costumerById.phoneNumber().getNumber());
    }


}