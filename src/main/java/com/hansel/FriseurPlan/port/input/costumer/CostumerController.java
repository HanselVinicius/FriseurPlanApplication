package com.hansel.FriseurPlan.port.input.costumer;

import com.hansel.FriseurPlan.core.domain.costumer.Costumer;
import com.hansel.FriseurPlan.core.application.usecase.costumer.command.CostumerCommandsUseCase;
import com.hansel.FriseurPlan.core.application.usecase.dto.CostumerDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/costumers")
@RequiredArgsConstructor
public class CostumerController {

    private final CostumerCommandsUseCase costumerCommandsUseCase;

    @PostMapping
    public Costumer createCostumer(@RequestBody @Valid CostumerDto costumerDto) {
        return this.costumerCommandsUseCase.createCostumer(costumerDto);
    }

}
