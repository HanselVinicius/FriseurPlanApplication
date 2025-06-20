package com.hansel.FriseurPlan.infra.port.output.entities.hairdresser;

import com.hansel.FriseurPlan.core.domain.PhoneNumber;
import com.hansel.FriseurPlan.core.domain.hairdresser.Hairdresser;
import com.hansel.FriseurPlan.infra.port.output.entities.AddressVo;
import com.hansel.FriseurPlan.infra.port.output.entities.EmailVo;
import com.hansel.FriseurPlan.infra.port.output.entities.PhoneNumberConverter;
import com.hansel.FriseurPlan.infra.port.output.entities.appointment.AppointmentEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "hairdressers")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SQLRestriction("deleted_at IS NULL")
public class HairdresserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Embedded
    private EmailVo email;
    @Convert(converter = PhoneNumberConverter.class)
    private PhoneNumber phoneNumber;
    @Embedded
    private AddressVo address;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "hairdresser_id")
    private final List<AppointmentEntity> appointments = new ArrayList<>();
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;


    public Hairdresser toHairdresserDomain() {
        return Hairdresser.create(
            this.id,
            this.name,
            this.appointments.stream().map(AppointmentEntity::toAppointmentDomain).toList(),
            this.phoneNumber,
            this.email.toEmailDomain(),
            this.address.toAddressDomain()
        );
    }


}
