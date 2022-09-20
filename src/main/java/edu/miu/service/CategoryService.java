package edu.miu.service;


import edu.miu.dto.CategoryDto;
import edu.miu.entity.Product;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> findAll();
    CategoryDto getCategoryById (int id);
    void saveCategoryDto (CategoryDto categoryDto);
    void deleteCategoryDtoById (int id);
    CategoryDto updateCategoryDto(int id, CategoryDto categoryDto);
    List<Product> findAllProductsByCateogryId(int id);

}
