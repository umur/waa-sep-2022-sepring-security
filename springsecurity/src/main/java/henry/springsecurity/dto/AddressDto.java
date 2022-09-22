package henry.springsecurity.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddressDto implements Serializable {
    private int id;
    private String street;
    private String zip;
    private String city;
    @JsonBackReference
    private UserDto user;
}