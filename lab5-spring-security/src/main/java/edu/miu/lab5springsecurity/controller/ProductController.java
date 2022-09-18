package edu.miu.lab5springsecurity.controller;

import edu.miu.lab6springsecurity.dto.ProductDto;
import edu.miu.lab6springsecurity.dto.ReviewDto;
import edu.miu.lab6springsecurity.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    final private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ProductDto save(@RequestBody ProductDto productDto) {
        return productService.save(productDto);
    }
    @GetMapping
    public List<ProductDto> findAll(){
        return productService.findAll();
    }

    @PostMapping("/{id}/reviews")
    public ReviewDto saveReview(@PathVariable int id, @RequestBody ReviewDto reviewDto) {
        return productService.saveReview(id, reviewDto);
    }
}
