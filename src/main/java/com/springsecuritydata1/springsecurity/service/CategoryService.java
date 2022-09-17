package com.springsecuritydata1.springsecurity.service;

import com.springsecuritydata1.springsecurity.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> getAllCategory();

    CategoryDTO SaveCategory(CategoryDTO categoryDTO);

    CategoryDTO getCategoryById(Integer id);

    CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer id);
}
