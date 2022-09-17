package com.springsecuritydata1.springsecurity.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    private Integer id;
    private  String email;
    private String password;
    private String firstName;
    private String lastName;

    @JsonBackReference
    private List<ReviewDTO> reviews;


    private AddressDTO address;
}
