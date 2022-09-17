package com.springsecuritydata1.springsecurity.controller;

import com.springsecuritydata1.springsecurity.dto.ProductDTO;
import com.springsecuritydata1.springsecurity.dto.ReviewDTO;
import com.springsecuritydata1.springsecurity.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping

    public ProductDTO save(@RequestBody ProductDTO productDTO){
        return productService.saveProduct(productDTO);
    }

    @GetMapping
    public List<ProductDTO> getAllProduct(){
        return productService.getAllProduct();
    }

    @GetMapping("/{id}")
    public ProductDTO findProductByID(@PathVariable Integer id){
        return productService.getProductById(id);
    }


    @GetMapping("/filter")
    public List<ProductDTO> getProductsByMinPrice(@RequestParam Integer minPrice){
        return productService.getProductsByMinPrice(minPrice);
    }

    @GetMapping("/keyword")
    public List<ProductDTO> findProductContainingKeyword(@RequestParam String keyword){
        return productService.findProductContainingKeyword(keyword);
    }

    @GetMapping("/{id}/reviews")
    public List<ReviewDTO> findReviewsOfProduct(@PathVariable Long id){
        return productService.findReviewsOfProduct(id);
    }

    @PutMapping("/{id}")
    public ProductDTO update(@PathVariable Integer id, @RequestBody ProductDTO productDTO){
        return productService.updateProduct( productDTO,id);
    }

    @DeleteMapping("/{id}")
    public ProductDTO delete(@PathVariable Integer id){
        return productService.deleteProduct(id);



    }}