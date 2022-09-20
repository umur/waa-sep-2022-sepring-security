package edu.miu.service.imp;


import edu.miu.dto.CategoryDto;
import edu.miu.entity.Category;
import edu.miu.entity.Product;
import edu.miu.repo.CategoryRepo;
import edu.miu.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService {
    @Autowired
    CategoryRepo categoryRepo;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<CategoryDto> findAll() {
        return mapCategoryToCategoryDtos(categoryRepo.findAll());
    }
    private List<CategoryDto> mapCategoryToCategoryDtos(Iterable<Category> categories) {
        List<CategoryDto> categoryDtos = new ArrayList<>();
        for(Category c : categories) {
            categoryDtos.add(modelMapper.map(c, CategoryDto.class));
        }
        return categoryDtos;
    }

    @Override
    public CategoryDto getCategoryById(int id) {
        return mapCategoryToCategory(categoryRepo.findById(id).get());
    }
    private CategoryDto mapCategoryToCategory(Category category) {
        return modelMapper.map(category, CategoryDto.class);
    }
    @Override
    public void saveCategoryDto(CategoryDto categoryDto) {
        categoryRepo.save(mapCategoryDtoToCategory(categoryDto));
    }
    private Category mapCategoryDtoToCategory(CategoryDto categoryDto) {
        return modelMapper.map(categoryDto, Category.class);
    }

    @Override
    public void deleteCategoryDtoById(int id) {
        categoryRepo.deleteById(id);
    }

    @Override
    public CategoryDto updateCategoryDto(int id, CategoryDto categoryDto) {
        deleteCategoryDtoById(id);
        saveCategoryDto(categoryDto);
        return categoryDto;
    }

    @Override
    public List<Product> findAllProductsByCateogryId(int id) {
        return categoryRepo.findAllProductsByCategoryId(id);
    }
}
