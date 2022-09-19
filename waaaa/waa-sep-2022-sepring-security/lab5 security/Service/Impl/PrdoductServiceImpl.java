package miu.edu.lab3.Service.Impl;

import miu.edu.lab3.DTO.ProductsDTO;
import miu.edu.lab3.DTO.ReviewDTO;
import miu.edu.lab3.Repo.ProductRepository;
import miu.edu.lab3.Service.ProductServiceInterface;
import miu.edu.lab3.entity.Products;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PrdoductServiceImpl implements ProductServiceInterface {


    @Autowired
    ProductRepository productRepository;
    @Autowired
    ModelMapper modelMapper;


    public List<ProductsDTO> findAll(){
       return  productRepository.findAll().stream()
                .map(a->modelMapper.map(a,ProductsDTO.class)).toList();
    }

    public ProductsDTO deleteById(int id){

        return modelMapper.map(productRepository.deleteById(id),ProductsDTO.class);
    }

    public ProductsDTO findById(int id){

        return modelMapper.map(productRepository.findById(id),ProductsDTO.class);

    }
@Override
    public ProductsDTO save(int id,ProductsDTO products){
        return modelMapper.map(productRepository.save
                (id,modelMapper.map(products, Products.class)),ProductsDTO.class);
    }

    public List<ProductsDTO> findProductsByPriceGreaterThan(double minPrice){
        return productRepository.findProductsByPriceGreaterThan(minPrice).stream()
                .map(a->modelMapper.map(a,ProductsDTO.class)).toList();
    }
    public List<ProductsDTO> findProductsByPriceLessThanAAndAndCategoryName(double price,String catagoryName){

        return productRepository.findProductsByPriceLessThanAAndAndCategoryName(price,catagoryName).stream()
                .map(a->modelMapper.map(a,ProductsDTO.class)).toList();
    }

    public List<ProductsDTO> findProductsByNameContainingIgnoreCase(String name){
       return productRepository.findProductsByNameContainingIgnoreCase(name).stream()
                .map(a->modelMapper.map(a,ProductsDTO.class)).toList();
    }
   public Products findReviewByProductsById(int id){
        return productRepository.findProductsById(id);
    }
}
