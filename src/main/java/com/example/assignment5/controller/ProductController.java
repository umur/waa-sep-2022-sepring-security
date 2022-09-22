package com.example.assignment5.controller;

import com.example.assignment5.dto.ProductDto;
import com.example.assignment5.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<ProductDto> getAll(){
        return productService.getAll();
    }

    @PostMapping
    public ProductDto create(@RequestBody ProductDto productDto){
        return productService.create(productDto);
    }
}
