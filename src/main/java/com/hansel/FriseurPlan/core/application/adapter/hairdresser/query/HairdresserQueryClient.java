package com.hansel.FriseurPlan.core.application.adapter.hairdresser.query;

import com.hansel.FriseurPlan.core.domain.email.Email;
import com.hansel.FriseurPlan.core.domain.hairdresser.Hairdresser;

public interface HairdresserQueryClient {

    Hairdresser getHairdresserByEmail(Email email);

    Hairdresser getHairdresserById(Long id);

}
