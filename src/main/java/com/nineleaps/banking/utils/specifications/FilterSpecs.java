package com.nineleaps.banking.utils.specifications;

import org.springframework.data.jpa.domain.Specification;

public class FilterSpecs {

    public static <T> Specification<T> getAccountBySpecs(Integer accountId) {
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("account"), accountId);
        });
    }
}
