package miu.edu.lab3.Service;

import miu.edu.lab3.DTO.UserDTO;
import miu.edu.lab3.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserServiceInterface {
    public List<UserDTO> findAll() ;

    public void update(UserDTO us);

    public UserDTO findById(int id);

    public void createUser(UserDTO user);

    public UserDTO deleteById(int id);
}
