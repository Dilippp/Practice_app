package com.nineleaps.banking.utils.specifications;

import java.util.Map;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

@Setter
public class SearchBuilder<T> {

    public static String DEFAULT_REGEX =
            "(\\w+?)([:~<>]\\*?)([0-9]{4}-[0-1][0-9]-[0-3][0-9]-?[0-9]+[.]?[0-9]*|[^,]*\\w+[^,]*),";
    /** Creates a search Specification based on a passed in set of query parameters */
    public Specification<T> search(Map<String, String> queryParameters) {
        SpecificationBuilder builder = new SpecificationBuilder();
        if (queryParameters.containsKey("search")) {
            processSearch(queryParameters, builder, "");
        }
        if (queryParameters.containsKey("search-operator")) {
            String searchOperator = queryParameters.get("search-operator");
            if (searchOperator != null) {
                return builder.build(searchOperator);
            }
        }
        return builder.build();
    }

    private void processSearch(
            Map<String, String> queryParameters, SpecificationBuilder builder, String regex) {}
}
