package com.example.boardmanager.mapper;

public interface MapperToEntity<T, S> {
    T getEntity(S dto);
}
