package com.hansel.FriseurPlan.infra.port.output.entities.appointment;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface AppointmentEntityRepository extends JpaRepository<AppointmentEntity, Long>, JpaSpecificationExecutor<AppointmentEntity> {
    @Modifying
    @Transactional
    @Query("""
                UPDATE AppointmentEntity ap 
                SET ap.deletedAt = :now 
                WHERE ap.id = :id
            """)
    void deleteAppointment(@Param("id") Long id, @Param("now") LocalDateTime now);
}
