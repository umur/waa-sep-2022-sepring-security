package edu.miu.demo.controller;

import edu.miu.demo.dto.ProductDto;
import edu.miu.demo.service.impl.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
@RequiredArgsConstructor
@CrossOrigin
public class ProductController {
    private final ProductServiceImpl service;


    @GetMapping
    public List<ProductDto> getAll() {
        return service.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductDto> getById(@PathVariable Long id) {
        return service.getById(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ProductDto save(@RequestBody ProductDto product) {
        return service.save(product);
    }

    @PutMapping("{id}")
    public ProductDto update(@PathVariable Long id, @RequestBody ProductDto product) {
        product.setId(id);
        return service.save(product);
    }

    @DeleteMapping
    public void delete(Long id) {
        service.delete(id);
    }
}