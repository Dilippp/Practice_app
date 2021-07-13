package com.nineleaps.banking.utils.specifications;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;

public class SpecificationBuilder {

    private final List<SearchCriteria> params;

    public SpecificationBuilder() {
        params = new ArrayList<>();
    }

    SpecificationBuilder with(String key, String operation, Object value) {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    <T> Specification<T> build(String... searchOperator) {
        if (params.size() == 0) {
            return null;
        }
        List<Specification<T>> specs = new ArrayList<>();
        for (SearchCriteria param : params) {
            specs.add(new SearchSpecification(param));
        }
        Specification<T> result = specs.get(0);

        if (searchOperator != null
                && searchOperator.length > 0
                && searchOperator[0].equals(SearchOperator.OR.toString())) {
            for (int i = 1; i < specs.size(); i++) {
                result = Specification.where(result).or(specs.get(i));
            }
        } else {
            for (int i = 0; i < specs.size(); i++) {
                result = Specification.where(result).and(specs.get(i));
            }
        }
        return result;
    }
}
