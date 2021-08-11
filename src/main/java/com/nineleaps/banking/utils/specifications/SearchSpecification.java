package com.nineleaps.banking.utils.specifications;

import com.nineleaps.banking.exception.BadRequestException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class SearchSpecification<T> implements Specification<T> {

    private final SearchCriteria criteria;

    public SearchSpecification(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(
            Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (root.get(criteria.getKey()).getJavaType() == LocalDate.class) {
            return getDateBasedPredicate(root, criteriaBuilder);
        } else {
            return getPredicate(root, criteriaBuilder);
        }
    }

    private Predicate getPredicate(Root<T> root, CriteriaBuilder builder) {
        switch (criteria.getOperation()) {
            case ">":
                return builder.greaterThanOrEqualTo(
                        root.get(criteria.getKey()), criteria.getValue().toString());
            case "<":
                return builder.lessThanOrEqualTo(
                        root.get(criteria.getKey()), criteria.getValue().toString());
            case "~":
                return builder.equal(root.get(criteria.getKey()), criteria.getValue().toString());
            case "~*":
                return builder.equal(
                        builder.lower(root.get(criteria.getKey())),
                        criteria.getValue().toString().toLowerCase());
            case ":":
                if (root.get(criteria.getKey()).getJavaType() == String.class) {
                    return builder.like(
                            root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
                }
                break;
            default:
                throw new BadRequestException("Unsupported search operation");
        }
        throw new BadRequestException("Unsupported search operation");
    }

    private Predicate getDateBasedPredicate(Root<T> root, CriteriaBuilder builder) {
        LocalDate date = getDate();
        switch (criteria.getOperation()) {
            case ">":
                return builder.greaterThanOrEqualTo(root.get(criteria.getKey()), date);
            case "<":
                return builder.lessThanOrEqualTo(root.get(criteria.getKey()), date);
            case "~":
                return builder.equal(root.get(criteria.getKey()), date);
            default:
                throw new BadRequestException("Unsupported search operation");
        }
    }

    private LocalDate getDate() {
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(criteria.getValue().toString(), dtf);
        } catch (Exception e) {
            throw new IllegalArgumentException(
                    criteria.getValue().toString() + ": date should be of the format yyyy-MM-dd");
        }
    }
}
