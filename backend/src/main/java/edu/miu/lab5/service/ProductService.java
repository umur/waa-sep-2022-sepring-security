package edu.miu.lab5.service;

import edu.miu.lab5.dto.ProductDto;

import java.util.List;

public interface ProductService {

    List<ProductDto> findAll();
    ProductDto findById(int id);
    ProductDto add(ProductDto product);
    void update(int id, ProductDto product);
    void deleteById(int id);
    List<ProductDto> findAllByPriceGreaterThan(float minPrice);
    List<ProductDto> findAllByCategoryIsAndPriceLessThan(int categoryId, float maxPrice);
    List<ProductDto> findAllByNameContains(String keyword);

}