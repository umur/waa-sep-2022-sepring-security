package edu.miu.lab5springsecurity.service;

import edu.miu.lab6springsecurity.dto.ProductDto;
import edu.miu.lab6springsecurity.dto.ReviewDto;

import java.util.List;

public interface ProductService {
    public ProductDto save(ProductDto productDto);
    public List<ProductDto> findAll();

    public ReviewDto saveReview(int productId, ReviewDto reviewDto);
}
