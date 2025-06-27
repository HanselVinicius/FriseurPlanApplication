package com.hansel.FriseurPlan.core.application.usecase.email;

import com.hansel.FriseurPlan.core.application.usecase.costumer.dto.CostumerReturnDto;
import com.hansel.FriseurPlan.core.application.usecase.costumer.query.CostumerQueryUseCase;
import com.hansel.FriseurPlan.core.application.usecase.hairdresser.dto.HairdresserReturnDto;
import com.hansel.FriseurPlan.core.application.usecase.hairdresser.query.HairdresserQueryUseCase;
import com.hansel.FriseurPlan.core.domain.Address;
import com.hansel.FriseurPlan.core.domain.PhoneNumber;
import com.hansel.FriseurPlan.core.domain.costumer.Costumer;
import com.hansel.FriseurPlan.core.domain.email.Email;
import com.hansel.FriseurPlan.core.domain.hairdresser.Hairdresser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidateEmailIsUniqueUseCaseTest {

    @Mock
    private CostumerQueryUseCase costumerQueryUseCase;
    @Mock
    private HairdresserQueryUseCase hairdresserQueryUseCase;
    @InjectMocks
    private ValidateEmailIsUniqueUseCase validateEmailIsUniqueUseCase;

    private Email costumerEmail;
    private Email hairdresserEmail;
    private PhoneNumber phoneNumber;
    private Address address;
    private Hairdresser hairdresser;
    private Costumer costumer;
    private HairdresserReturnDto hairdresserReturnDto;
    private CostumerReturnDto costumerReturnDto;

    @BeforeEach
    void setUp() {
        this.costumerEmail = Email.create("costumerEmail@gmail.com", true);
        this.hairdresserEmail = Email.create("hairdresserEmail@gmail.com",true);
        this.phoneNumber = PhoneNumber.create("16992977903");
        this.address = Address.create("123 Main St", 100, "Springfield", "SP", 12345678L);
        this.hairdresser = Hairdresser.create(null, "hairdresser", new ArrayList<>(), phoneNumber, hairdresserEmail, address);
        this.costumer = Costumer.create(null, "teste", "16992977903",costumerEmail);
        this.hairdresserReturnDto = HairdresserReturnDto.fromDomain(hairdresser);
        this.costumerReturnDto = CostumerReturnDto.fromDomain(costumer);
    }


    @Test
    void validateShouldReturnTrueWhenEmailIsUnique() {
        when(costumerQueryUseCase.getCostumerByEmail(any(Email.class))).thenReturn(null);
        when(hairdresserQueryUseCase.getHairdresserByEmail(any(Email.class))).thenReturn(null);

        boolean isUnique = this.validateEmailIsUniqueUseCase.validate(costumerEmail);

        assertTrue(isUnique);
    }

    @Test
    void shouldReturnFalseWhenCostumerEmailIsNotUnique() {
        when(costumerQueryUseCase.getCostumerByEmail(costumerEmail)).thenReturn(costumerReturnDto);

        boolean isUnique = this.validateEmailIsUniqueUseCase.validate(costumerEmail);
        assertFalse(isUnique);
    }

    @Test
    void shouldReturnFalseWhenHairdresserEmailIsNotUnique() {
        when(hairdresserQueryUseCase.getHairdresserByEmail(hairdresserEmail)).thenReturn(hairdresserReturnDto);

        boolean isUnique = this.validateEmailIsUniqueUseCase.validate(hairdresserEmail);
        assertFalse(isUnique);
    }
}