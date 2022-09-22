package com.example.assignment5.mapper;

import java.util.List;

public interface BaseMapper<D, E> {
    E toEntity(D dto);
    D toDTO(E entity);
    List<E> toEntity(List<D> dtoList);
    List<D> toDTO(List<E> entityList);

}
