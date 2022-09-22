package henry.springsecurity.controller;

import henry.springsecurity.dto.ProductDto;
import henry.springsecurity.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/products")
//@PreAuthorize("hasRole('ROLE_ADMIN')")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public void save(@RequestBody ProductDto productDto) {
        productService.save(productDto);
    }

    @GetMapping
    public List<ProductDto> findAll() {
        return productService.findAll();
    }

    @PutMapping("/{productId}")
    public void update(@PathVariable int productId, @RequestBody ProductDto productDto) {
        productService.update(productId, productDto);
    }

    @DeleteMapping("/{productId}")
    public ProductDto delete(@PathVariable int productId) {
        return productService.delete(productId);
    }

    @GetMapping("/search/min-price/{price}")
    public List<ProductDto> searchByMinPrice(@PathVariable double price) {
        return productService.findWithMinPrice(price);
    }

    @GetMapping("/search/name/{keyword}")
    public List<ProductDto> searchByName(@PathVariable String keyword) {
        return productService.findWithName(keyword);
    }

    @GetMapping("/search/category-and-max-price/{categoryId}/{price}")
    public List<ProductDto> searchByCategoryAndMaxPrice(@PathVariable int categoryId, @PathVariable double price) {
        return productService.findWithCategoryAndMaxPrice(categoryId, price);
    }
}
