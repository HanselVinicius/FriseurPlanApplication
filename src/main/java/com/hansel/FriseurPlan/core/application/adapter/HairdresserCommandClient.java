package com.hansel.FriseurPlan.core.application.adapter;

import com.hansel.FriseurPlan.core.domain.hairdresser.Hairdresser;

public interface HairdresserCommandClient {
    Hairdresser createHairdresser(Hairdresser hairdresser);
}
