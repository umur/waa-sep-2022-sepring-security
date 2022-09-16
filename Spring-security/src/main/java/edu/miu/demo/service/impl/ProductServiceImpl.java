package edu.miu.demo.service.impl;


import edu.miu.demo.dto.ProductDto;
import edu.miu.demo.model.Product;
import edu.miu.demo.repo.ProductRepo;
import edu.miu.demo.security.WaaUserDetails;
import edu.miu.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private ProductRepo productRepo;

    private ModelMapper modelMapper;

    @Override
    public List<ProductDto> getAll() {
        return productRepo.findAll()
                .stream()
                .map(u -> modelMapper.map(u, ProductDto.class))
                .collect(Collectors.toList());
    }
    @Override
    public Optional<ProductDto> getById(Long id) {
        return productRepo.findById(id)
                .map(u -> modelMapper.map(u, ProductDto.class));
    }

    @Override
    public ProductDto save(ProductDto product) {
        WaaUserDetails userDetails = (WaaUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (product.getId() == null) {
            product.setCreatedBy(userDetails.getId());
            product.setCreatedAt(Instant.now());
        } else {
            product.setUpdatedBy(userDetails.getId());
            product.setUpdatedAt(Instant.now());
        }
        return modelMapper.map(productRepo.save(modelMapper.map(product, Product.class)), ProductDto.class);
    }

    @Override
    public void delete(Long id) {
        productRepo.deleteById(id);
    }


}
