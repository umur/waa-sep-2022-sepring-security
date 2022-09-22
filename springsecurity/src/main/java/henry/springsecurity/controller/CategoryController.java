package henry.springsecurity.controller;

import henry.springsecurity.dto.CategoryDto;
import henry.springsecurity.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public void save(@RequestBody CategoryDto categoryDto) {
        categoryService.save(categoryDto);
    }

    @GetMapping
    public List<CategoryDto> findAll() {
        return categoryService.findAll();
    }

    @PutMapping("/{categoryId}")
    public void update(@PathVariable int categoryId, @RequestBody CategoryDto categoryDto) {
        categoryService.update(categoryId, categoryDto);
    }

    @DeleteMapping("/{categoryId}")
    public CategoryDto delete(@PathVariable int categoryId) {
        return categoryService.delete(categoryId);
    }
}
