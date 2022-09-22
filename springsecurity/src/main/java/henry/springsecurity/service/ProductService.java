package henry.springsecurity.service;

import henry.springsecurity.dto.ProductDto;

import java.util.List;

public interface ProductService {
    void save(ProductDto productDto);
    List<ProductDto> findAll();
    void update(int productId, ProductDto productDto);
    ProductDto delete(int productId);


    List<ProductDto> findWithMinPrice(double minPrice);
    List<ProductDto> findWithName(String keyword);
    List<ProductDto> findWithCategoryAndMaxPrice(int categoryId, double maxPrice);
}
