package henry.springsecurity.service;

import henry.springsecurity.dto.UserDto;

import java.util.List;

public interface UserService {
    void save(UserDto userDto);
    List<UserDto> findAll();
    void update(int userId, UserDto userDto);
    UserDto delete(int userId);
}
