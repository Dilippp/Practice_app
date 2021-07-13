package com.nineleaps.banking.utils.specifications;

import java.util.UUID;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

/** Specification to filter by a field of type UUID */
public class UUIDFieldSpecification<T> implements Specification<T> {
    private final String fieldId;
    private final UUID fieldValue;

    public UUIDFieldSpecification(String fieldId, UUID fieldValue) {
        this.fieldId = fieldId;
        this.fieldValue = fieldValue;
    }

    @Override
    public Predicate toPredicate(
            Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (fieldId == null || fieldValue == null) {
            return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
        }
        return criteriaBuilder.equal(root.get(fieldId), fieldValue);
    }
}
