package com.example.boardmanager.mapper;

public interface MapperToDto<T, S> {
    T getDto(S entity);
}
