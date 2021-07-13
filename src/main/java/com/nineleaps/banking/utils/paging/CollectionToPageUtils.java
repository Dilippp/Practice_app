package com.nineleaps.banking.utils.paging;

import static java.util.stream.Collectors.toList;

import com.nineleaps.banking.mapper.ModelMapperEntityToDto;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class CollectionToPageUtils {

    public static <E, D> Page<D> toPage(
            List<E> items, Pageable pageable, ModelMapperEntityToDto<E, D> mapper) {
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), items.size());

        return new PageImpl<D>(
                items.subList(start, end).stream().map(mapper::toDto).collect(toList()),
                pageable,
                pageable.getPageSize());
    }
}
