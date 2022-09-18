package edu.miu.lab5springsecurity.service.impl;

import edu.miu.lab6springsecurity.dto.ProductDto;
import edu.miu.lab6springsecurity.dto.ReviewDto;
import edu.miu.lab6springsecurity.entity.Product;
import edu.miu.lab6springsecurity.entity.Review;
import edu.miu.lab6springsecurity.repository.ProductRepo;
import edu.miu.lab6springsecurity.repository.ReviewRepo;
import edu.miu.lab6springsecurity.repository.UserRepo;
import edu.miu.lab6springsecurity.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ReviewRepo reviewRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductDto save(ProductDto productDto) {
        var product = modelMapper.map(productDto, Product.class);
        var email = ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        var user = userRepo.findByEmail(email);
        product.setUser(user);
        return modelMapper.map(productRepo.save(product), ProductDto.class);
    }

    @Override
    public List<ProductDto> findAll() {
        var products = new ArrayList<ProductDto>();
        productRepo.findAll().forEach(p -> products.add(modelMapper.map(p, ProductDto.class)));
        return products;
    }

    @Override
    public ReviewDto saveReview(int productId, ReviewDto reviewDto) {
        var product = productRepo.findById(productId).orElseGet(null);
        if (product != null) {
            var review = modelMapper.map(reviewDto, Review.class);
            review.setProduct(product);
            review = reviewRepo.save(review);
            return modelMapper.map(review, ReviewDto.class);
        }
        return null;
    }
}
