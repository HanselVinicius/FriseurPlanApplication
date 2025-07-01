package com.hansel.FriseurPlan.infra.port.output.entities.hairdresser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HairdresserEntityRepository extends JpaRepository<HairdresserEntity,Long> {

    @Query(
            value = """
                    SELECT * FROM hairdressers h WHERE h.email = ?1 AND h.verified IS TRUE AND h.deleted_at IS NULL
            """, nativeQuery = true
    )
    Optional<HairdresserEntity> getHairdresserEntityByEmail(String email);

}
