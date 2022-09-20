package edu.miu.lab5.controller;

import edu.miu.lab5.dto.ProductDto;
import edu.miu.lab5.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping()
    public List<ProductDto> get() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable int id) {
        return productService.findById(id);
    }

    @PostMapping()
    public ProductDto post(@RequestBody ProductDto c) {
        return productService.add(c);
    }

    @PutMapping("/{id}")
    public boolean put(@PathVariable int id, @RequestBody ProductDto c) {
        productService.update(id, c);
        return true;
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable int id) {
        productService.deleteById(id);
        return true;
    }

    @GetMapping("/minPrice/{minPrice}")
    public List<ProductDto> byMinPrice(@PathVariable float minPrice) {
        return productService.findAllByPriceGreaterThan(minPrice);
    }

    @GetMapping("/category/{categoryId}/maxPrice/{maxPrice}")
    public List<ProductDto> byCatAndMaxPrice(@PathVariable int categoryId, @PathVariable float maxPrice) {
        return productService.findAllByCategoryIsAndPriceLessThan(categoryId, maxPrice);
    }

    @GetMapping("/search")
    public List<ProductDto> search(@RequestParam String keyword) {
        return productService.findAllByNameContains(keyword);
    }

}
