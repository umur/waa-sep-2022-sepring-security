package edu.miu.lab5.service.impl;

import edu.miu.lab5.dto.CategoryDto;
import edu.miu.lab5.dto.ProductDto;
import edu.miu.lab5.entity.Category;
import edu.miu.lab5.entity.Product;
import edu.miu.lab5.repo.CategoryRepo;
import edu.miu.lab5.repo.ProductRepo;
import edu.miu.lab5.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final CategoryRepo categoryRepo;
    private final ProductRepo productRepo;
    private final ModelMapper modelMapper;

    @Override
    public List<ProductDto> findAll() {
        return ((List<Product>) productRepo.findAll()).stream().map(p -> modelMapper.map(p, ProductDto.class)).toList();
    }

    public ProductDto findById(int id) {
        Product product = productRepo.findById(id).orElse(null);
        return product == null
            ? null
            : modelMapper.map(product, ProductDto.class);
    }

    @Override
    public ProductDto add(ProductDto product) {
        Product p = modelMapper.map(product, Product.class);

        CategoryDto category = product.getCategory();
        if (category != null) {
            categoryRepo.findById(category.getId()).ifPresent(p::setCategory);
        }

        Product saved = productRepo.save(p);
        return modelMapper.map(saved, ProductDto.class);
    }

    @Override
    public void update(int id, ProductDto product) {
        Product c = modelMapper.map(product, Product.class);
        productRepo.save(c);
    }

    @Override
    public void deleteById(int id) {
        productRepo.deleteById(id);
    }

    @Override
    public List<ProductDto> findAllByPriceGreaterThan(float minPrice) {
        return productRepo.findAllByPriceGreaterThan(minPrice)
                          .stream()
                          .map(p -> modelMapper.map(p, ProductDto.class))
                          .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> findAllByCategoryIsAndPriceLessThan(int categoryId, float maxPrice) {
        Category category = categoryRepo.findById(categoryId).orElse(null);

        return category == null
            ? null
            : productRepo.findAllByCategoryIsAndPriceLessThan(category, maxPrice)
                         .stream()
                         .map(p -> modelMapper.map(p, ProductDto.class))
                         .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> findAllByNameContains(String keyword) {
        return productRepo.findAllByNameContains(keyword)
                          .stream()
                          .map(p -> modelMapper.map(p, ProductDto.class))
                          .collect(Collectors.toList());
    }

}
