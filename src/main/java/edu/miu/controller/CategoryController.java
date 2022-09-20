package edu.miu.controller;


import edu.miu.dto.CategoryDto;
import edu.miu.entity.Product;
import edu.miu.repo.ProductRepo;
import edu.miu.service.imp.CategoryServiceImp;
import edu.miu.service.imp.ProductServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryServiceImp categoryService;
    @Autowired
    ProductRepo productRepo;

    @GetMapping
    public List<CategoryDto> findAll(){
        return categoryService.findAll();
    }
    @GetMapping("/{id}")
    public CategoryDto getCategoryById(@PathVariable int id) {
        return categoryService.getCategoryById(id);
    }
    @PostMapping
    public void saveCategoryDto(@RequestBody CategoryDto categoryDto){
        categoryService.saveCategoryDto(categoryDto);
    }
    @DeleteMapping("/{id}")
    public void deleteCategoryDto (@PathVariable int id) {
        categoryService.deleteCategoryDtoById(id);

    }
    @PutMapping("/{id}")
    public CategoryDto updateCategoryDto(@PathVariable int id, @RequestBody CategoryDto categoryDto) {
        return categoryService.updateCategoryDto(id, categoryDto);
    }
    @GetMapping("/{id}/products")
    public List<Product> findAllById(@PathVariable int id) {
        return categoryService.findAllProductsByCateogryId(id);
    }


}
