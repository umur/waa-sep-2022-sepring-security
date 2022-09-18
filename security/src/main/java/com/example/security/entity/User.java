package com.example.security.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "users")
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String email;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    //@JsonBackReference
    private List<Role> roles;
    @OneToOne(mappedBy = "user")
    @JsonManagedReference
    private Product product;

    public User(String password, String username) {
        this.email=username;
        this.password=password;
    }

    public User() {

    }

    public User(int id, String password, String username) {
        this.id=id;
        this.email=username;
        this.password=password;
    }
}
