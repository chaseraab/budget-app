package com.budget_app.specification.base;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BaseSpecification<T> implements Specification<T> {

    private final Map<String, Object> filters;

    public BaseSpecification(Map<String, Object> filters) {
        this.filters = filters;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (filters != null && !filters.isEmpty()) {
            filters.forEach((key, value) -> {
                if (value != null) {
                    if (value instanceof String) {
                        predicates.add(criteriaBuilder.like(
                                criteriaBuilder.lower(root.get(key)), "%" + value.toString().toLowerCase() + "%"
                        ));
                    } else {
                        predicates.add(criteriaBuilder.equal(root.get(key), value));
                    }
                }
            });
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

}
