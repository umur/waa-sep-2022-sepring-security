package com.example.security.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import org.keycloak.jose.jwk.JWK;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ManyToOne
    private Category category;
    @OneToOne
    @JsonBackReference
    private User user;

}
