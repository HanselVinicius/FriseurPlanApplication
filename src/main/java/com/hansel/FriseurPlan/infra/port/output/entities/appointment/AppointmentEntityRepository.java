package com.hansel.FriseurPlan.infra.port.output.entities.appointment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentEntityRepository extends JpaRepository<AppointmentEntity,Long>, JpaSpecificationExecutor<AppointmentEntity> {}
