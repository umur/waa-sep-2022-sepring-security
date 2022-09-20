package com.example.springsecurity.Service.Impl;


import com.example.springsecurity.DTO.ProductDto;
import com.example.springsecurity.Model.AppUser;
import com.example.springsecurity.Model.Product;
import com.example.springsecurity.Repository.AppUserRepo;
import com.example.springsecurity.Repository.ProductRepo;
import com.example.springsecurity.Security.Model.AppUserDetail;
import com.example.springsecurity.Service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepo productRepo;
    private AppUserRepo appUserRepo;
    private ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepo productRepo,ModelMapper modelMapper, AppUserRepo appUserRepo){
        this.productRepo=productRepo;
        this.modelMapper=modelMapper;
        this.appUserRepo=appUserRepo;
    }

    @Override
    public List<ProductDto> findAllProducts() {
        List<Product> products= (List<Product>) productRepo.findAll();
      return   products.stream()
                .map(product -> modelMapper.map(product,ProductDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto findProductByID(int id) {
        if(productRepo.findById(id).isPresent()){
            ProductDto product=modelMapper.map(productRepo.findById(id).get(),ProductDto.class);
            return product;
        }
        return null;
    }

    @Override
    public ProductDto addProduct(ProductDto product) {
        Product p = modelMapper.map(product,Product.class);
        AppUserDetail user = (AppUserDetail)  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        AppUser appUser= appUserRepo.findAppUserByUsername(user.getUsername());
        p.setUser(appUser);
        productRepo.save(p);
        return product;
    }

    @Override
    public ProductDto updateProduct(int id, ProductDto product) {
        Product p =modelMapper.map(product,Product.class);
        p.setId(id);
         productRepo.save(p);
         return product;
    }

    @Override
    public void deleteProductById(int id) {
        productRepo.deleteById(id);
    }






}
