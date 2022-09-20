package com.example.springsecurity.Controller;

import com.example.springsecurity.DTO.ProductDto;
import com.example.springsecurity.Service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/products")
public class ProductController {

    ProductService productService;

    public ProductController(ProductService productService){
        this.productService=productService;
    }

    @GetMapping
    public List<ProductDto> findAllProducts() {
        return productService.findAllProducts();
    }

    @GetMapping("/{id}")
    public ProductDto findProductByID(@PathVariable int id) {
        return productService.findProductByID(id);
    }

    @PostMapping
    public ProductDto addProduct(@RequestBody ProductDto product) {
        return productService.addProduct(product);
    }

    @PutMapping
    public ProductDto updateProduct(@RequestBody ProductDto product, @RequestParam int id) {
        return productService.updateProduct(id,product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable int id) {
    }

}
