package miu.edu.lab3.entity;




import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    int id;
    String email;
    String password;
    @OneToMany(mappedBy = "Id_User")
    List<Review>  reviews;
    @OneToOne
    Address address;


}
