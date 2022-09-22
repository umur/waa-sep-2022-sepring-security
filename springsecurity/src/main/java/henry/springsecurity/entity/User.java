package henry.springsecurity.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "LabUser")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
//    @JsonBackReference
    @JoinColumn(name = "user_id")
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    List<Review> reviews;
//    @JsonManagedReference
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    Address address;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable
    private List<Role> roles;
}
