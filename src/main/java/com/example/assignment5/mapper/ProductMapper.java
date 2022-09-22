package com.example.assignment5.mapper;

import com.example.assignment5.dto.ProductDto;
import com.example.assignment5.entity.Product;
import org.mapstruct.Mapper;

@Mapper
public interface ProductMapper extends BaseMapper<ProductDto, Product> {
}
