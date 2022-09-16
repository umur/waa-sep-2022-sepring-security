package edu.miu.demo.service;



import edu.miu.demo.dto.ProductDto;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<ProductDto> getAll();
    Optional<ProductDto> getById(Long id);
    ProductDto save(ProductDto user);
    void delete(Long id);
}
