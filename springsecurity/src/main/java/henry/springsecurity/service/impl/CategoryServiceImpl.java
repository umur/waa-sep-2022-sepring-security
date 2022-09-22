package henry.springsecurity.service.impl;

import henry.springsecurity.dto.CategoryDto;
import henry.springsecurity.entity.Category;
import henry.springsecurity.mapper.ListMapper;
import henry.springsecurity.repo.CategoryRepo;
import henry.springsecurity.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepo categoryRepo;
    private final ModelMapper modelMapper;
    private final ListMapper listMapper;

    @Override
    public void save(CategoryDto categoryDto) {
        categoryRepo.save(modelMapper.map(categoryDto, Category.class));
    }

    @Override
    public List<CategoryDto> findAll() {
        return listMapper.map((List<Category>)categoryRepo.findAll(), CategoryDto.class);
    }

    @Override
    public void update(int categoryId, CategoryDto categoryDto) {
        Category category = categoryRepo.findById(categoryId).orElse(null);
        if (category != null) {
            Category newCategory = modelMapper.map(categoryDto, Category.class);
            newCategory.setId(categoryId);
            categoryRepo.save(newCategory);
        }
    }

    @Override
    public CategoryDto delete(int categoryId) {
        Category category = categoryRepo.findById(categoryId).orElse(null);
        categoryRepo.deleteById(categoryId);
        return category != null ? modelMapper.map(category, CategoryDto.class) : null;
    }
}
