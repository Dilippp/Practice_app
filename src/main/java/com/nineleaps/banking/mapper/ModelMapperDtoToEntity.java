package com.nineleaps.banking.mapper;

public interface ModelMapperDtoToEntity<D, E> {

    E toEntity(D d);
}
