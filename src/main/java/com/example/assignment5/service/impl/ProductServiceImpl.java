package com.example.assignment5.service.impl;

import com.example.assignment5.dto.ProductDto;
import com.example.assignment5.entity.Product;
import com.example.assignment5.entity.User;
import com.example.assignment5.mapper.ProductMapper;
import com.example.assignment5.repository.ProductRepo;
import com.example.assignment5.repository.UserRepo;
import com.example.assignment5.security.UserDetailsImpl;
import com.example.assignment5.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {
    private final ProductRepo productRepo;
    private final ProductMapper productMapper;
    private final UserRepo userRepo;

    @Override
    public List<ProductDto> getAll() {
        return productMapper.toDTO(productRepo.findAll());
    }

    @Override
    public ProductDto create(ProductDto productDto) {
        Product product = productMapper.toEntity(productDto);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Optional<User> user = userRepo.findById(userDetails.getId());
        product.setUser(user.get());
        return productMapper.toDTO(productRepo.save(product));
    }
}
