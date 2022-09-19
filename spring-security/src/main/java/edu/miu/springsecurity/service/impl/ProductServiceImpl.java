package edu.miu.springsecurity.service.impl;

import edu.miu.springsecurity.dto.ProductDto;
import edu.miu.springsecurity.entity.Product;
import edu.miu.springsecurity.repository.ProductRepo;
import edu.miu.springsecurity.repository.UserRepo;
import edu.miu.springsecurity.security.MyUserDetails;
import edu.miu.springsecurity.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;
    private final UserRepo userRepo;
    private final ModelMapper mapper;


    @Override
    public void save(ProductDto p) {
        MyUserDetails userDetail = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var user = userRepo.findByEmail(userDetail.getUsername());
        var product = mapper.map(p,Product.class);
        product.setUser(user);
        productRepo.save(product);
    }

    @Override
    public void delete(int id) {
        productRepo.deleteById(id);
    }

    @Override
    public Product getById(int id) {
        return productRepo.findById(id).get();
    }

    @Override
    public List<Product> getAll() {
        var result = new ArrayList<Product>();
        productRepo.findAll().forEach(result::add);
        return result;
    }
}
