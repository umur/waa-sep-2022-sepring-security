package edu.miu.demo.service;


import edu.miu.demo.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDto> getAll();
    Optional<UserDto> getById(Long id);
    UserDto save(UserDto user);
    void delete(Long id);
}
