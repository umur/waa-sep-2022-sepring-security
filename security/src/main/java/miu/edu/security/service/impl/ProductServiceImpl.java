package miu.edu.security.service.impl;

import miu.edu.security.entity.Product;
import miu.edu.security.repository.ProductRepo;
import miu.edu.security.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Override
    public void save(Product p) {
        productRepo.save(p);
    }

    @Override
    public void delete(Long id) {
        productRepo.deleteById(id);
    }

    @Override
    public Product getById(Long id) {
        return productRepo.findById(id).get();
    }

    @Override
    public List<Product> getAll() {
        List<Product> result = new ArrayList<>();
        productRepo.findAll().forEach(result::add);
        return result;
    }
}
