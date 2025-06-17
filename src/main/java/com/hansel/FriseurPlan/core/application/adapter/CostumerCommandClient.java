package com.hansel.FriseurPlan.core.application.adapter;

import com.hansel.FriseurPlan.core.domain.costumer.Costumer;

public interface CostumerCommandClient {
    Costumer createCostumer(Costumer costumer);
}
