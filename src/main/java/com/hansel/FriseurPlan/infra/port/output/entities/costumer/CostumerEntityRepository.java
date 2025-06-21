package com.hansel.FriseurPlan.infra.port.output.entities.costumer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CostumerEntityRepository extends JpaRepository<CostumerEntity, Long> {

    @Query(value = """
                    SELECT * FROM costumers c WHERE c.email = ?1 AND c.deleted_at IS NULL AND c.verified IS TRUE
            """, nativeQuery = true)
    CostumerEntity getCostumerEntityByEmail(String email);

}
