package com.nineleaps.banking.utils.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

/** Specification to filter by a field of type String using the case sensitive LIKE operator */
public class StringFieldSpecification<T> implements Specification<T> {
    private final String fieldId;
    private final String fieldValue;

    public StringFieldSpecification(String fieldId, String fieldValue) {
        this.fieldId = fieldId;
        this.fieldValue = fieldValue;
    }

    @Override
    public Predicate toPredicate(
            Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (fieldId == null || fieldValue == null) {
            return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
        }
        return criteriaBuilder.like(
                criteriaBuilder.lower(root.get(fieldId)), "%" + fieldValue.toLowerCase() + "%");
    }
}
