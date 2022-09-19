package miu.edu.lab3.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {
    @Id
    int id;
    String Street;
    int zip;
    String city;
    @OneToOne
    UserDTO user;
}
