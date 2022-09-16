package edu.miu.demo.dto;


import edu.miu.demo.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private Double price;

    private Instant createdAt;
    private Long createdBy;
    private Instant updatedAt;
    private Long updatedBy;

}
