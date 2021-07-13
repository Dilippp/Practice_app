package com.nineleaps.banking.utils.specifications;

import com.nineleaps.banking.exception.BadRequestException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        if (root.get(criteria.getKey()).getJavaType() == Date.class) {
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
        Date date = getDate();
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

    private Date getDate() {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            return formatter.parse(criteria.getValue().toString());
        } catch (ParseException e) {
            throw new IllegalArgumentException(
                    criteria.getValue().toString() + ": date should be of the format yyyy-MM-dd");
        }
    }
}
