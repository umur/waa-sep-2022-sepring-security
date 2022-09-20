package edu.miu.lab5.service;

import edu.miu.lab5.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    List<CategoryDto> findAll();
    CategoryDto findById(int id);
    CategoryDto add(CategoryDto category);
    void update(int id, CategoryDto category);
    void deleteById(int id);

}