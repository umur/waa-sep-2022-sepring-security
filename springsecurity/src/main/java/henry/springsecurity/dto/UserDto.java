package henry.springsecurity.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto implements Serializable {
    private int id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    @JsonManagedReference
    private List<ReviewDto> reviews;
    @JsonManagedReference
    private AddressDto address;
}