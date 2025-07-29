package com.hansel.FriseurPlan.infra.adapter.output.client.appointment.specifications;

import com.hansel.FriseurPlan.infra.adapter.output.entities.appointment.AppointmentEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@RequiredArgsConstructor
public class GetAppointmentByRelationSpecs implements Specification<AppointmentEntity> {

    private final Long hairdresserId;
    private final Long costumerId;


    @Override
    public Predicate toPredicate(Root<AppointmentEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        Predicate predicate = criteriaBuilder.conjunction();


        if(hairdresserId != null){
            predicate = criteriaBuilder.and(predicate,criteriaBuilder.equal(root.get("hairdresserEntity").get("id"), hairdresserId));
        }

        if(costumerId != null){
            predicate = criteriaBuilder.and(predicate,criteriaBuilder.equal(root.get("costumerEntity").get("id"), costumerId));
        }

        return predicate;
    }
}
