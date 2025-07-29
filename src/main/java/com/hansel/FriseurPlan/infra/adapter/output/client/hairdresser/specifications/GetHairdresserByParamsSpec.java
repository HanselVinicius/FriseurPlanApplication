package com.hansel.FriseurPlan.infra.adapter.output.client.hairdresser.specifications;

import com.hansel.FriseurPlan.infra.adapter.output.entities.hairdresser.HairdresserEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@RequiredArgsConstructor
public class GetHairdresserByParamsSpec implements Specification<HairdresserEntity> {

    private final String name;

    @Override
    public Predicate toPredicate(Root<HairdresserEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Predicate predicate = criteriaBuilder.conjunction();

        if (name != null && !name.isBlank()) {
            String likePattern = "%" + name + "%";
            predicate = criteriaBuilder.and(
                    predicate,
                    criteriaBuilder.like(
                            root.get("name"),
                            likePattern
                    )
            );
        }

        return predicate;
    }
}
