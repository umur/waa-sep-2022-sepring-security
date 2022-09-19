package miu.edu.lab3.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductsDTO {
   @Id
   @GeneratedValue(strategy= GenerationType.AUTO)
    int id;
    String name;
    double price;
    int rating;
    @ManyToOne
    CategoryDTO category;

}
