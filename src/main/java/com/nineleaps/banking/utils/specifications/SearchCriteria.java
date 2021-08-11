package com.nineleaps.banking.utils.specifications;

import lombok.Getter;

@Getter
public class SearchCriteria {

    private final String key;
    private final String operation;
    private final Object value;

    public SearchCriteria(String key, String operation, Object value) {
        this.key = key;
        this.operation = operation;
        this.value = value;
    }
}
