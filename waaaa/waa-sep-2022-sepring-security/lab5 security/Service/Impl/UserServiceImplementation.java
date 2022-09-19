package miu.edu.lab3.Service.Impl;

import miu.edu.lab3.DTO.UserDTO;
import miu.edu.lab3.Repo.UserRepository;
import miu.edu.lab3.Service.UserServiceInterface;
import miu.edu.lab3.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImplementation implements UserServiceInterface {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;


    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map(a -> modelMapper.map(a, UserDTO.class)).toList();
    }

    public void update(UserDTO us) {
        userRepository.save(modelMapper.map(us, User.class));
    }

    public UserDTO findById(int id) {
        return modelMapper.map(userRepository.findById(id),UserDTO.class);
    }

    public void createUser(UserDTO user){

        userRepository.save(modelMapper.map(user, User.class));
    }

    public UserDTO deleteById(int id){
        UserDTO u=findById(id);
      userRepository.deleteById(id);
        return u;
    }
}
