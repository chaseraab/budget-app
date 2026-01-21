package com.budget_app.search.specification.base;

import com.budget_app.search.SearchOperator;
import com.budget_app.search.searchCriteria.SearchCriteria;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BaseSpecification<T> implements Specification<T> {

    private final Map<String, Object> filters;

    public BaseSpecification(Map<String, Object> filters) {
        this.filters = filters;
    }

    private SearchCriteria parse(String key, Object value) {

        if (key.endsWith("_gt"))  return new SearchCriteria(key.replace("_gt", ""), SearchOperator.GT, value);
        if (key.endsWith("_gte")) return new SearchCriteria(key.replace("_gte", ""), SearchOperator.GTE, value);
        if (key.endsWith("_lt"))  return new SearchCriteria(key.replace("_lt", ""), SearchOperator.LT, value);
        if (key.endsWith("_lte")) return new SearchCriteria(key.replace("_lte", ""), SearchOperator.LTE, value);

        return new SearchCriteria(key, SearchOperator.EQ, value);
    }

    private Object convertValue(Class<?> targetType, Object value) {
        if (targetType.equals(LocalDate.class)) {
            return LocalDate.parse(value.toString());
        }
        if (targetType.equals(BigDecimal.class)) {
            return new BigDecimal(value.toString());
        }
        if (targetType.equals(Long.class)) {
            return Long.valueOf(value.toString());
        }
        return value;
    }

//    @Override
//    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//        List<Predicate> predicates = new ArrayList<>();
//
//        if (filters != null && !filters.isEmpty()) {
//            filters.forEach((key, value) -> {
//                if (value != null) {
//                    if (value instanceof String) {
//                        predicates.add(criteriaBuilder.like(
//                                criteriaBuilder.lower(root.get(key)), "%" + value.toString().toLowerCase() + "%"
//                        ));
//                    } else {
//                        predicates.add(criteriaBuilder.equal(root.get(key), value));
//                    }
//                }
//            });
//        }
//        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
//    }

    private Predicate buildGreaterThan(CriteriaBuilder cb, Path<?> path, Object value) {
        Class<?> type = path.getJavaType();
        if (type.equals(LocalDate.class)) {
            return cb.greaterThan(path.as(LocalDate.class), (LocalDate) value);
        }
        try {
            return cb.greaterThan(path.as(Float.class), (Float) Float.parseFloat((String)value));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Predicate toPredicate(Root<T> root,
                                 CriteriaQuery<?> query,
                                 CriteriaBuilder cb) {

        List<Predicate> predicates = new ArrayList<>();

        filters.forEach((key, rawValue) -> {

            SearchCriteria criteria = parse(key, rawValue);
            Path<?> path = root.get(criteria.field());
            Class<?> type = path.getJavaType();

            Object value = convertValue(type, rawValue);

            switch (criteria.operator()) {

                case GT -> predicates.add(
                        buildGreaterThan(cb, path, value)
                );

                case GTE -> predicates.add(
                        cb.greaterThanOrEqualTo(path.as(Comparable.class), (Comparable) value)
                );

                case LT -> predicates.add(
                        cb.lessThan(path.as(Comparable.class), (Comparable) value)
                );

                case LTE -> predicates.add(
                        cb.lessThanOrEqualTo(path.as(Comparable.class), (Comparable) value)
                );

                case EQ -> predicates.add(cb.equal(path, value));
            }
        });

        return cb.and(predicates.toArray(new Predicate[0]));
    }

}
