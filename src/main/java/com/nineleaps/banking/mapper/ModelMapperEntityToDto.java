package com.nineleaps.banking.mapper;

public interface ModelMapperEntityToDto <E, D> {

    D toDto(E e);
}
