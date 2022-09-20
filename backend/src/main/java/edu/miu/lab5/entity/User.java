package edu.miu.lab5.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "lab5_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "lab5_users_roles")
    private List<Role> roles;

    @OneToMany(mappedBy = "user")
    private List<Review> reviews;

    @OneToOne(mappedBy = "user")
    @JsonBackReference
    private Product product;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private Set<OffensiveWordAudit> offensiveWordAudits;

    @OneToOne
    private Address address;
}
