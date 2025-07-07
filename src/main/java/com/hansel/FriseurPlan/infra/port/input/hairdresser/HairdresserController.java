package com.hansel.FriseurPlan.infra.port.input.hairdresser;

import com.hansel.FriseurPlan.core.application.usecase.hairdresser.command.HairdresserCommandUseCase;
import com.hansel.FriseurPlan.core.application.usecase.hairdresser.dto.GetHairdresserDto;
import com.hansel.FriseurPlan.core.application.usecase.hairdresser.dto.HairdresserDto;
import com.hansel.FriseurPlan.core.application.usecase.hairdresser.dto.HairdresserReturnDto;
import com.hansel.FriseurPlan.core.application.usecase.hairdresser.query.HairdresserQueryUseCase;
import com.hansel.FriseurPlan.core.domain.email.Email;
import com.hansel.FriseurPlan.core.domain.hairdresser.Hairdresser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("v1/hairdressers")
@RequiredArgsConstructor
public class HairdresserController {

    private final HairdresserCommandUseCase hairdresserCommandUseCase;
    private final HairdresserQueryUseCase hairdresserQueryUseCase;

    @PostMapping
    public ResponseEntity<Hairdresser> createHairdresser(@RequestBody @Valid HairdresserDto hairdresserDto, @AuthenticationPrincipal Jwt jwt) {
        Email email = Email.create(jwt.getClaimAsString("email"), jwt.getClaimAsBoolean("email_verified"));
        Hairdresser hairdresser = this.hairdresserCommandUseCase.createHairdresser(hairdresserDto, email);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(hairdresser.getId())
                .toUri();
        return ResponseEntity.created(location).body(hairdresser);
    }

    @GetMapping("/email")
    public ResponseEntity<HairdresserReturnDto> getHairdresserByEmail(@AuthenticationPrincipal Jwt jwt) {
        Email email = Email.create(jwt.getClaimAsString("email"), jwt.getClaimAsBoolean("email_verified"));
        HairdresserReturnDto returnDto = this.hairdresserQueryUseCase.getHairdresserByEmail(email);
        return ResponseEntity.ok(returnDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HairdresserReturnDto> getHairdresserById(@PathVariable Long id) {
        HairdresserReturnDto returnDto = this.hairdresserQueryUseCase.getHairdresserById(id);
        return ResponseEntity.ok(returnDto);
    }

    @GetMapping
    public ResponseEntity<Page<HairdresserReturnDto>> getHairdressers(
            @RequestParam(name = "name",required = false) String name,
            Pageable pageable
    ) {
        GetHairdresserDto getHairdresserDto = new GetHairdresserDto(pageable,name);
        Page<HairdresserReturnDto> result = this.hairdresserQueryUseCase.getAllHairdressers(getHairdresserDto);
        return ResponseEntity.ok(result);
    }

}
