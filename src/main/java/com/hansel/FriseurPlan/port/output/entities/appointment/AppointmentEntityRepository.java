package com.hansel.FriseurPlan.port.output.entities.appointment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentEntityRepository extends JpaRepository<AppointmentEntity,Long> {
}
