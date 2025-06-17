package com.hansel.FriseurPlan.port.output.entities.costumer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CostumerEntityRepository extends JpaRepository<CostumerEntity, Long> {}
