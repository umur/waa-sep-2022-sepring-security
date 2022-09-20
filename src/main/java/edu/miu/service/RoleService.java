package edu.miu.service;

import edu.miu.dto.UserDto;
import edu.miu.entity.Role;
import edu.miu.entity.User;

import java.util.List;

public interface RoleService {
    List<Role> findAll();

    Role findUserById(int id);

    void deleteUserById(int id);

    void save(Role role);

    void update(int id, Role userDto);

}
