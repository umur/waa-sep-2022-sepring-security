package edu.miu.lab5.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductDto {

    private int id;
    private String name;
    private Float price;
    private Integer rating;
    private CategoryDto category;

}
