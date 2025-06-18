package com.hansel.FriseurPlan.port.output.entities.costumer;

import com.hansel.FriseurPlan.core.domain.PhoneNumber;
import com.hansel.FriseurPlan.port.output.entities.EmailVo;
import com.hansel.FriseurPlan.port.output.entities.PhoneNumberConverter;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;

@Entity
@Table(name = "costumers")
@Getter
@Setter
@SQLRestriction("deleted_at IS NULL")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CostumerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Convert(converter = PhoneNumberConverter.class)
    private PhoneNumber phoneNumber;
    @Embedded
    private EmailVo email;
    @CreationTimestamp
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;


}
