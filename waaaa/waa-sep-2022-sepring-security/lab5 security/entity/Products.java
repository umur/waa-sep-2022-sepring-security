package miu.edu.lab3.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Products {
   @Id
    int id;
    String name;
    double price;
    int rating;
    @ManyToOne
    Category category;

}
