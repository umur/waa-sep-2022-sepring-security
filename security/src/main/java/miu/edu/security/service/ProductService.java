package miu.edu.security.service;

import miu.edu.security.entity.Product;

import java.util.List;

public interface ProductService {
    void save(Product p);

    void delete(Long id);

    Product getById(Long id);

    List<Product> getAll();
}
