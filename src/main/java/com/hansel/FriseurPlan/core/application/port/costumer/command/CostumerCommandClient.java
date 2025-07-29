package com.hansel.FriseurPlan.core.application.port.costumer.command;

import com.hansel.FriseurPlan.core.domain.costumer.Costumer;

public interface CostumerCommandClient {
    Costumer createCostumer(Costumer costumer);
}
