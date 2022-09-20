package com.example.springsecurity.Service;

import com.example.springsecurity.DTO.ProductDto;
import com.example.springsecurity.Model.Product;

import java.util.List;

public interface ProductService {
    public List<ProductDto> findAllProducts();
    public ProductDto findProductByID(int id);
    public ProductDto addProduct(ProductDto product);
    public ProductDto updateProduct(int id, ProductDto product);
    public void deleteProductById(int id);


}
