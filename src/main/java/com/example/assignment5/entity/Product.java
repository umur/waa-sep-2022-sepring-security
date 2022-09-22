package com.example.assignment5.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "product")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    private int price;

    @ManyToOne
    private User user;
    @ManyToOne
    private Category category;

    public void setUser(User user){
        this.user = user;
        user.addProduct(this);
    }

    public void setCategory(Category category){
        this.category = category;
        category.addProduct(this);
    }

}