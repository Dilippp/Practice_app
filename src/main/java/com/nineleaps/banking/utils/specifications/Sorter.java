package com.nineleaps.banking.utils.specifications;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class Sorter {

    Sort sortArray = null;

    public static Pageable sort(Map<String, String> queryParameters, Pageable pageable) {
        Sorter sorter = new Sorter();
        pageable = sorter.multidimensionalSort(queryParameters, pageable);
        return pageable;
    }

    private Sort simpleSort(String sortOrder, String sortField, Boolean isProjection) {
        if (isProjection) {
            sortField = sortField.replaceAll("_", ".");
        }
        Sort.Direction sortDirection = Sort.Direction.ASC;
        if (sortOrder.equals("DESC")) {
            sortDirection = Sort.Direction.DESC;
        }
        if (sortArray == null) {
            sortArray = Sort.by(sortDirection, sortField);
        } else {
            sortArray = sortArray.and(Sort.by(sortDirection, sortField));
        }
        return sortArray;
    }

    private Pageable multidimensionalSort(Map<String, String> queryParameters, Pageable pageable) {
        return multidimensionalSort(queryParameters, pageable, false);
    }

    private Pageable multidimensionalSort(
            Map<String, String> queryParameters, Pageable pageable, boolean isProjection) {
        if (queryParameters.containsKey("sort")) {
            Pattern pattern = Pattern.compile("([.\\w]+?)(,|<|>)(\\w+?):");
            String sort = queryParameters.get("sort");
            if (sort != null) {
                Sort sortArray = Sort.unsorted();
                Matcher matcher = pattern.matcher(sort + ":");
                while (matcher.find()) {
                    sortArray = simpleSort(matcher.group(3), matcher.group(1), isProjection);
                }
                pageable =
                        PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sortArray);
            }
        }
        return pageable;
    }

    private Map<String, String> combineSortParams(
            Map<String, String> queryParameters, SortQuery sortQuery1, SortQuery sortQuery2) {
        if (queryParameters.containsKey("sort")) {
            return requestedSortByClient(queryParameters, sortQuery1);
        }
        if (!queryParameters.containsKey("sort")) {
            return defaultSort(queryParameters, sortQuery1, sortQuery2);
        }
        return queryParameters;
    }

    private Map<String, String> defaultSort(
            Map<String, String> queryParameters, SortQuery sortQuery1, SortQuery sortQuery2) {

        queryParameters.put(
                "sort",
                sortQuery2.getField()
                        + ","
                        + sortQuery2.getOrder()
                        + ":"
                        + sortQuery1.getField()
                        + ","
                        + sortQuery1.getOrder());
        return queryParameters;
    }

    private Map<String, String> requestedSortByClient(
            Map<String, String> queryParameters, SortQuery sortQuery1) {
        String value =
                queryParameters
                        .get("sort")
                        .concat(":" + sortQuery1.getField() + "," + sortQuery1.getOrder());
        queryParameters.put("sort", value);
        return queryParameters;
    }
}
