package henry.springsecurity.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String street;
    private String zip;
    private String city;
//    @JsonBackReference
    @OneToOne(mappedBy = "address", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    User user;

    @Override
    public String toString() {
        return "Address {" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", zip='" + zip + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
