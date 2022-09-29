package edu.miu.lab5.controller;


import edu.miu.lab5.aspect.WaaOffensiveAnotate;
import edu.miu.lab5.entity.Product;
import edu.miu.lab5.entity.Review;
import edu.miu.lab5.service.IProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@CrossOrigin
public class ProductController {

    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @WaaOffensiveAnotate
    public void save(@RequestBody Product p) {
        productService.save(p);
    }

    @GetMapping
    public List<Product> getAll() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable int id) {
        var product = productService.getById(id);
        return ResponseEntity.ok(product);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        productService.delete(id);
    }

    @PutMapping("/")
    public void update(@RequestBody Product product) {
        productService.save(product);
    }

    @GetMapping("/{id}/reviews")
    public ResponseEntity<Review> getReviewsByProductId(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
    }

}
