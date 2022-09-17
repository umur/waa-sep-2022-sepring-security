package com.springsecuritydata1.springsecurity.service.impl;

import com.springsecuritydata1.springsecurity.dto.ProductDTO;
import com.springsecuritydata1.springsecurity.dto.ReviewDTO;
import com.springsecuritydata1.springsecurity.entity.Product;
import com.springsecuritydata1.springsecurity.repo.ProductRepo;
import com.springsecuritydata1.springsecurity.repo.UserRepo;
import com.springsecuritydata1.springsecurity.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepo productRepo;
    private final ModelMapper modelMapper;
    private final UserRepo userRepo;
    @Override
    public ProductDTO saveProduct(ProductDTO productDTO) {
        Object principalObj= SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if(principalObj instanceof UserDetails){
            username=((UserDetails)principalObj).getUsername();

        }else {
            username=principalObj.toString();
        }
        productDTO.setUser(userRepo.findByEmail(username));

        productRepo.save(modelMapper.map(productDTO, Product.class));
        return null;
    }

    @PreAuthorize("hasRole('ROLE_GOLD')")
    @Override
    public List<ProductDTO> getAllProduct() {
        return productRepo.findAll().stream().map(product -> modelMapper.map(product,ProductDTO.class)).collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProductById(Integer id) {
        return modelMapper.map(productRepo.findById(id),ProductDTO.class);
    }

    @Override
    public List<ProductDTO> getProductsByMinPrice(Integer minPrice) {
        return null;
    }

    @Override
    public List<ProductDTO> findProductContainingKeyword(String keyword) {
        return null;
    }

    @Override
    public List<ReviewDTO> findReviewsOfProduct(Long id) {
        return null;
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO, Integer id) {
        return null;
    }

    @Override
    public ProductDTO deleteProduct(Integer id) {
        return null;
    }

    @PreAuthorize("hasRole('ROLE_GOLD') or hasRole('ROLE_ADMIN')")
    public List<ProductDTO> getAll() {
        List<ProductDTO> result = new ArrayList<>();
        result=productRepo.findAll().stream().map(a->modelMapper.map(a,ProductDTO.class)).collect(Collectors.toList());
        return result;
    }



}
