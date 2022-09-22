package com.example.assignment5.service;

import com.example.assignment5.dto.ProductDto;
import com.example.assignment5.entity.Product;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAll();

    ProductDto create(ProductDto productDto);
}
