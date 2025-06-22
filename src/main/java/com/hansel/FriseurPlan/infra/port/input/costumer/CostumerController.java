package com.hansel.FriseurPlan.infra.port.input.costumer;

import com.hansel.FriseurPlan.core.application.usecase.costumer.query.CostumerQueryUseCase;
import com.hansel.FriseurPlan.core.domain.email.Email;
import com.hansel.FriseurPlan.core.domain.costumer.Costumer;
import com.hansel.FriseurPlan.core.application.usecase.costumer.command.CostumerCommandUseCase;
import com.hansel.FriseurPlan.core.application.usecase.dto.CostumerDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("v1/costumers")
@RequiredArgsConstructor
public class CostumerController {

    private final CostumerCommandUseCase costumerCommandUseCase;
    private final CostumerQueryUseCase costumerQueryUseCase;

    @PostMapping
    public ResponseEntity<Costumer> createCostumer(
            @RequestBody @Valid CostumerDto costumerDto,
            @AuthenticationPrincipal Jwt jwt
    ) {
        Email email = Email.create(jwt.getClaimAsString("email"), jwt.getClaimAsBoolean("email_verified"));
        Costumer costumer = this.costumerCommandUseCase.createCostumer(costumerDto, email);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(costumer.getId())
                .toUri();
        return ResponseEntity.created(location).body(costumer);
    }

    @GetMapping
    public ResponseEntity<Costumer> getCostumerByEmail(
            @AuthenticationPrincipal Jwt jwt
    ) {
        Email email = Email.create(jwt.getClaimAsString("email"), jwt.getClaimAsBoolean("email_verified"));
        return ResponseEntity.ok(this.costumerQueryUseCase.getCostumerByEmail(email));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Costumer> getCostumerById(@PathVariable Long id){
        return ResponseEntity.ok(this.costumerQueryUseCase.getCostumerById(id));
    }

}
