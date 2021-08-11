package com.nineleaps.banking.utils.specifications;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

@Setter
public class SearchBuilder<T> {

    public static final String DEFAULT_REGEX =
            "(\\w+?)([:~<>]\\*?)([0-9]{4}-[0-1][0-9]-[0-3][0-9]-?[0-9]+[.]?[0-9]*|[^,]*\\w+[^,]*),";

    /** Creates a search Specification based on a passed in set of query parameters */
    public Specification<T> search(Map<String, String> queryParameters) {
        SpecificationBuilder builder = new SpecificationBuilder();
        if (queryParameters.containsKey("search")) {
            processSearch(queryParameters.get("search"), builder);
        }
        if (queryParameters.containsKey("search-operator")) {
            String searchOperator = queryParameters.get("search-operator");
            if (searchOperator != null) {
                return builder.build(searchOperator);
            }
        }
        return builder.build();
    }

    private void processSearch(String search, SpecificationBuilder builder) {
        if (search != null) {
            Pattern pattern = Pattern.compile(SearchBuilder.DEFAULT_REGEX);
            Matcher matcher = pattern.matcher(search + ",");
            while (matcher.find()) {
                builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
            }
        }
    }
}
