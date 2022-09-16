package miu.edu.security.service;

import miu.edu.security.entity.Product;
import java.util.List;

public interface ProductService {
    void create(Product product);

    List<Product> findAll();

    Product getById(int id);

    void deleteById(int id);

    void updateById(int id, Product product);
}
