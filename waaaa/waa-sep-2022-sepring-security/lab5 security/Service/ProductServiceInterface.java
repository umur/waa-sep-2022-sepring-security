package miu.edu.lab3.Service;

import miu.edu.lab3.DTO.ProductsDTO;
import miu.edu.lab3.entity.Products;

import java.util.List;

public interface ProductServiceInterface {
    public List<ProductsDTO> findAll();

    public ProductsDTO deleteById(int id);
    public ProductsDTO findById(int id);
    public ProductsDTO save(int id,ProductsDTO products);

    public List<ProductsDTO> findProductsByPriceGreaterThan(double minPrice);
    public List<ProductsDTO> findProductsByPriceLessThanAAndAndCategoryName(double price,String catagoryName);

    public List<ProductsDTO> findProductsByNameContainingIgnoreCase(String name);
    Products findReviewByProductsById(int id);
}
