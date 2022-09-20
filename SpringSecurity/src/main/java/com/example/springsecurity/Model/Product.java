package com.example.springsecurity.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private double price;
    private double rating;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="user_id")
    private AppUser user;

    @ManyToOne( cascade = CascadeType.PERSIST)
    @JoinColumn(name="category_id")
    private Category category;

}
