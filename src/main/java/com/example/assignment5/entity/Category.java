package com.example.assignment5.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "category")
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product){
        if(!this.products.contains(product)){
            this.products.add(product);
        }
        if(!this.equals(product.getCategory())){
            product.setCategory(this);
        }
    }
}
