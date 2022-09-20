package edu.miu.service.imp;

import edu.miu.dto.UserDto;
import edu.miu.entity.Role;
import edu.miu.entity.User;
import edu.miu.repo.RoleRepo;
import edu.miu.service.RoleService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class RoleServiceImp implements RoleService {
    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    ModelMapper modelMapper;
    @Override
    public List<Role> findAll() {
        return mapToRoles( roleRepo.findAll());
    }

    private List<Role> mapToRoles (Iterable<Role> roleIterable) {
        List<Role> roles = new ArrayList<>();
        for(Role r : roleIterable) {
            roles.add(modelMapper.map(r, Role.class));
        }
        return roles;
    }
    @Override
    public Role findUserById(int id) {
        return (roleRepo.findById(id).get());
    }


    @Override
    public void deleteUserById(int id) {

    }

    @Override
    public void save(Role role) {

    }

    @Override
    public void update(int id, Role userDto) {

    }
}
