package edu.miu.lab5springsecurity.service.impl;

import edu.miu.lab6springsecurity.dto.UserDto;
import edu.miu.lab6springsecurity.repository.UserRepo;
import edu.miu.lab6springsecurity.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<UserDto> findAll() {
        var users = new ArrayList<UserDto>();
        userRepo.findAll().forEach(u -> users.add(modelMapper.map(u, UserDto.class)));
        return users;
    }
}
