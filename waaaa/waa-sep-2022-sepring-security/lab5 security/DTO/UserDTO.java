package miu.edu.lab3.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    @Id
    int id;
    String email;
    String password;
    @OneToMany(mappedBy = "Id_user")
    List<ReviewDTO>  reviews;
    @OneToOne(mappedBy="Id_User")
    AddressDTO address;


}
