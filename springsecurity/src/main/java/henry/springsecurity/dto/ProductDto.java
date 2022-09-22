package henry.springsecurity.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductDto implements Serializable {
    private int id;
    private String name;
    private double price;
    private float rating;
    @JsonBackReference
    private CategoryDto category;
}
