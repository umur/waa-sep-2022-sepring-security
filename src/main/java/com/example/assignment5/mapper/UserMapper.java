package com.example.assignment5.mapper;

import com.example.assignment5.dto.UserDto;
import com.example.assignment5.entity.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<UserDto, User> {
}
