package com.hansel.FriseurPlan.port.input.costumer;

import com.hansel.FriseurPlan.core.domain.Email;
import com.hansel.FriseurPlan.core.domain.costumer.Costumer;
import com.hansel.FriseurPlan.core.application.usecase.costumer.command.CostumerCommandsUseCase;
import com.hansel.FriseurPlan.core.application.usecase.dto.CostumerDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/costumers")
@RequiredArgsConstructor
public class CostumerController {

    private final CostumerCommandsUseCase costumerCommandsUseCase;

    @PostMapping
    public ResponseEntity<Costumer> createCostumer(
            @RequestBody @Valid CostumerDto costumerDto,
            @AuthenticationPrincipal Jwt jwt
    ) {
        Email email = Email.create(jwt.getClaimAsString("email"), jwt.getClaimAsBoolean("email_verified"));

        return ResponseEntity.ok(this.costumerCommandsUseCase.createCostumer(costumerDto,email));
    }

}
