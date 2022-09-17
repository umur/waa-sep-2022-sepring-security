package com.springsecuritydata1.springsecurity.service;

import com.springsecuritydata1.springsecurity.dto.ProductDTO;
import com.springsecuritydata1.springsecurity.dto.ReviewDTO;

import java.util.List;

public interface ProductService {
    ProductDTO saveProduct(ProductDTO productDTO);

    List<ProductDTO> getAllProduct();

    ProductDTO getProductById(Integer id);

    List<ProductDTO> getProductsByMinPrice(Integer minPrice);

    List<ProductDTO> findProductContainingKeyword(String keyword);

    List<ReviewDTO> findReviewsOfProduct(Long id);

    ProductDTO updateProduct(ProductDTO productDTO, Integer id);

    ProductDTO deleteProduct(Integer id);
}
