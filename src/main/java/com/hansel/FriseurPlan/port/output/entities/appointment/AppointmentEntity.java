package com.hansel.FriseurPlan.port.output.entities.appointment;

import com.hansel.FriseurPlan.port.output.entities.appointment.vo.TimeRangeVo;
import com.hansel.FriseurPlan.port.output.entities.costumer.CostumerEntity;
import com.hansel.FriseurPlan.port.output.entities.hairdresser.HairdresserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
@Getter
@Setter
@SQLRestriction("deleted_at IS NULL")
public class AppointmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private TimeRangeVo timeRangeVo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hairdresser_id", nullable = false)
    private HairdresserEntity hairdresserEntity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "costumer_id", nullable = false)
    private CostumerEntity costumerEntity;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
