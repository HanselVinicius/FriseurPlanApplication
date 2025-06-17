package com.hansel.FriseurPlan.port.output.entities.hairdresser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HairdresserEntityRepository extends JpaRepository<HairdresserEntity,Long> {}
