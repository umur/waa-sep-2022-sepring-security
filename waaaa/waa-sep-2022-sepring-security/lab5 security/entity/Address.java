package miu.edu.lab3.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    int id;
    String Street;
    int zip;
    String city;
    @OneToOne(mappedBy="address")
    User user;
}
