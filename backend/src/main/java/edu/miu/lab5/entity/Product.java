package edu.miu.lab5.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private Float price;
    private Integer rating;

    @ManyToOne
    private Category category;

    @OneToOne
    @JsonManagedReference
    private User user;

    public void setUser(User user) {
        this.user = user;
        user.setProduct(this);
    }

}
