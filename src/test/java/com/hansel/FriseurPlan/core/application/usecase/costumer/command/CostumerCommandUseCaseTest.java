package com.hansel.FriseurPlan.core.application.usecase.costumer.command;

import com.hansel.FriseurPlan.core.application.adapter.costumer.command.CostumerCommandClient;
import com.hansel.FriseurPlan.core.domain.Email;
import com.hansel.FriseurPlan.core.domain.costumer.Costumer;
import com.hansel.FriseurPlan.core.application.usecase.dto.CostumerDto;
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
class CostumerCommandUseCaseTest {

    @Mock
    private CostumerCommandClient costumerCommandClient;
    @InjectMocks
    private CostumerCommandUseCase costumerCommandUseCase;

    private Email email;
    private CostumerDto costumerDto;
    private Costumer costumer;

    @BeforeEach
    void setUp() {
        email = Email.create("hanelvinicius@gmail.com", false);
        costumerDto = new CostumerDto("Hansel", "16992977903");
        costumer = Costumer.create(null, costumerDto.name(), costumerDto.phoneNumber(),email);
    }

    @Test
    void shouldCreateCostumer(){
        when(costumerCommandClient.createCostumer(any(Costumer.class))).thenReturn(costumer);

        Costumer result = this.costumerCommandUseCase.createCostumer(costumerDto,email);

        verify(costumerCommandClient).createCostumer(any(Costumer.class));
        assertNotNull(result);
        assertEquals("Hansel", result.getName());
        assertEquals("16992977903", result.getPhoneNumber().getNumber());
        assertNull(result.getId());
    }


}