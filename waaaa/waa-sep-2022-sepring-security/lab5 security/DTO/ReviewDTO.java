package miu.edu.lab3.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    int id;
    String comment;
    @ManyToOne
    UserDTO user;

}
