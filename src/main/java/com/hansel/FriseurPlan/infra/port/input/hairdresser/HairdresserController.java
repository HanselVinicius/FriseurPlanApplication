package com.hansel.FriseurPlan.infra.port.input.hairdresser;

import com.hansel.FriseurPlan.core.application.usecase.dto.HairdresserDto;
import com.hansel.FriseurPlan.core.application.usecase.hairdresser.command.HairdresserCommandUseCase;
import com.hansel.FriseurPlan.core.domain.Email;
import com.hansel.FriseurPlan.core.domain.hairdresser.Hairdresser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/hairdressers")
@RequiredArgsConstructor
public class HairdresserController {

    private final HairdresserCommandUseCase hairdresserCommandUseCase;

    @PostMapping
    public ResponseEntity<Hairdresser> createHairdresser(@RequestBody @Valid HairdresserDto hairdresserDto,@AuthenticationPrincipal Jwt jwt) {
        Email email = Email.create(jwt.getClaimAsString("email"), jwt.getClaimAsBoolean("email_verified"));
        Hairdresser hairdresser = this.hairdresserCommandUseCase.createHairdresser(hairdresserDto,email);
        return ResponseEntity.ok(hairdresser);
    }

}
