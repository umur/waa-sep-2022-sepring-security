package com.example.assignment5.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_table")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String username;
    private String password;
    private String email;
    private boolean isEnabled = true;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.PERSIST)
    private List<Role> roles = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.PERSIST)
    private List<Product> products = new ArrayList<>();

    public void setRoles(List<Role> roles){
        this.roles = roles;
        for(Role role: roles){
            if(!this.equals(role.getUser())){
                role.setUser(this);
            }
        }
    }

    public void setProducts(List<Product> products){
        this.products = products;
        for(Product product: products){
            if(!this.equals(product.getUser())){
                product.setUser(this);
            }
        }
    }

    public void addProduct(Product product){
        if(!this.products.contains(product)){
            this.products.add(product);
        }
        if(!this.equals(product.getUser())){
            product.setUser(this);
        }
    }
    public void addRole(Role role){
        if(!this.roles.contains(role)){
            this.roles.add(role);
        }
        if(!this.equals(role.getUser())){
            role.setUser(this);
        }
    }

}