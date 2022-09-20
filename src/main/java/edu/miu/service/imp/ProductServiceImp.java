package edu.miu.service.imp;


import edu.miu.dto.ProductDto;
import edu.miu.entity.Product;
import edu.miu.repo.ProductRepo;
import edu.miu.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService {
    private final ProductRepo productRepo;

    @Autowired
    ModelMapper modelMapper;


    @Override
    public Product save(ProductDto productDto) {

        return productRepo.save(modelMapper.map(productDto, Product.class));

    }

    @Override
    public void update(int id, ProductDto productDto) {
        var p =modelMapper.map(productDto,Product.class);
        p.setId(id);
        productRepo.save(p);


    }

    @Override
    public List<ProductDto> findAll() {
        return mapProductToProduct(productRepo.findAll());
    }
    private List<ProductDto>  mapProductToProduct(Iterable<Product> productIterable) {
        List<ProductDto> productDtos = new ArrayList<>();
        for(Product product : productIterable) {
            productDtos.add(modelMapper.map(product, ProductDto.class));
        }
        return productDtos;
    }

    @Override
    public ProductDto findProductById(int id) {
        return mapProductProductDto(productRepo.findById(id).get());
    }
    private ProductDto mapProductProductDto(Product product) {
        return modelMapper.map(product, ProductDto.class);
    }
    @Override
    public void deleteById( int id) {
        productRepo.deleteById(id);
    }

    @Override
    public List<ProductDto> findByPriceGreaterThan(float price) {
        return mapProductToProduct(productRepo.findByPriceGreaterThan(price));
    }

    @Override
    public List<ProductDto> findByCategoryAndPriceLessThan(float price) {
        return mapProductToProduct(productRepo.findByPriceGreaterThan(price));
    }

    @Override
    public List<ProductDto> findByNameContains(String keyword) {
        return mapProductToProduct(productRepo.findByNameContaining(keyword));
    }


}
