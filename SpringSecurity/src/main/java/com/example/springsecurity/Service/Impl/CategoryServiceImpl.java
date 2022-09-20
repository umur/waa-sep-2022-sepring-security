package com.example.springsecurity.Service.Impl;


import com.example.springsecurity.DTO.CategoryDto;
import com.example.springsecurity.Model.Category;
import com.example.springsecurity.Repository.CategoryRepo;
import com.example.springsecurity.Service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    CategoryRepo categoryRepo;
    private ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepo categoryRepo, ModelMapper modelMapper){
        this.categoryRepo=categoryRepo;
        this.modelMapper=modelMapper;
    }

    @Override
    public List<CategoryDto> findAllCategory() {
       List<Category> categories= (List) categoryRepo.findAll();
     return categories.stream()
             .map(category -> modelMapper.map(category,CategoryDto.class))
             .collect(Collectors.toList());
    }



    @Override
    public CategoryDto findCategoryByID(int id) {
       if(categoryRepo.findById(id).isPresent()){
           CategoryDto mappedcategory =modelMapper.map(categoryRepo.findById(id).get(),CategoryDto.class);
           return mappedcategory;
       }
       return null;
    }

    @Override
    public CategoryDto addCategory(CategoryDto category) {
            Category  mappedcategory =modelMapper.map(category,Category.class);
            categoryRepo.save(mappedcategory);
            return category;
    }

    @Override
    public CategoryDto updateCategory( CategoryDto category) {
        Category mappedcategory =modelMapper.map(category,Category.class);
          categoryRepo.save(mappedcategory);
        return category;
    }

    @Override
    public void deleteCategoryById(int id) {
        categoryRepo.deleteById(id);


    }


}
