package edu.miu.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;


    @OneToOne
    @JoinColumn(name = "home_address_id")

    private Address address;

    @OneToMany(mappedBy = "user")

    private List<Review> reviews;

   @JsonBackReference
    @ManyToMany(fetch = FetchType.EAGER)

    private List<Role> roles;


}
