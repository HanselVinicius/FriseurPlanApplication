package com.hansel.FriseurPlan.infra.port.output.entities.costumer;

import com.hansel.FriseurPlan.core.domain.PhoneNumber;
import com.hansel.FriseurPlan.core.domain.costumer.Costumer;
import com.hansel.FriseurPlan.infra.port.output.entities.EmailVo;
import com.hansel.FriseurPlan.infra.port.output.entities.PhoneNumberConverter;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.UpdateTimestamp;

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
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;


    public Costumer toCostumerDomain() {
        return Costumer.create(
            this.id,
            this.name,
            this.phoneNumber.getNumber(),
            this.email.toEmailDomain()
        );
    }

}
