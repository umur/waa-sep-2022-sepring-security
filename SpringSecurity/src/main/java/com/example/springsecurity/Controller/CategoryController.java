package com.example.springsecurity.Controller;



import com.example.springsecurity.DTO.CategoryDto;
import com.example.springsecurity.Service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/categories")
public class CategoryController {

   private CategoryService categoryService;

   public CategoryController(CategoryService categoryService){
       this.categoryService=categoryService;
   }

    @GetMapping
    public List<CategoryDto> findAllCategory() {
        List<CategoryDto> categories= (List)categoryService.findAllCategory();
        return categories;
    }

    @GetMapping("/{id}")
    public CategoryDto findCategoryByID(@PathVariable int id) {
        return categoryService.findCategoryByID(id);
    }

    @PostMapping
    public CategoryDto addCategory(@RequestBody CategoryDto category) {
        return categoryService.addCategory(category);
    }

    @PutMapping
    public CategoryDto updateCategory(@RequestBody CategoryDto category) {
        return categoryService.updateCategory(category);
    }

    @DeleteMapping("/{id}")
    public void deleteCategoryById(@PathVariable int id) {
        categoryService.deleteCategoryById(id);
    }
}
