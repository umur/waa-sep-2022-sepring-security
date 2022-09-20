package com.example.springsecurity.Service;


import com.example.springsecurity.DTO.CategoryDto;
import com.example.springsecurity.Model.Category;

import java.util.List;


public interface CategoryService {

    public List<CategoryDto> findAllCategory();
    public CategoryDto findCategoryByID(int id);
    public CategoryDto addCategory(CategoryDto category);
    public CategoryDto updateCategory(CategoryDto category);
    public void deleteCategoryById(int id);

}
