package com.springsecuritydata1.springsecurity.service;

import com.springsecuritydata1.springsecurity.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO SaveUser(UserDTO userDTO);

    List<UserDTO> getAllUser();

    UserDTO getUserById(Integer id);

    UserDTO updateUser(UserDTO userDTO, Integer id);
}
