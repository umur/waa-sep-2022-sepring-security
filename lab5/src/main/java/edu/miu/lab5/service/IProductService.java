package edu.miu.lab5.service;

;

import edu.miu.lab5.entity.Product;

import java.util.List;

public interface IProductService {

    void save(Product p);

    void delete(int id);

    Product getById(int id);

    List<Product> getAll();
}
