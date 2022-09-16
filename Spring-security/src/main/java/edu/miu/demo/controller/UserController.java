package edu.miu.demo.controller;

import edu.miu.demo.dto.UserDto;
import edu.miu.demo.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {
    private final UserServiceImpl service;

    @GetMapping
    public List<UserDto> getAll() {
        return service.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> getById(@PathVariable Long id) {
        return service.getById(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public UserDto save(@RequestBody UserDto user) {
        return service.save(user);
    }

    @PutMapping("{id}")
    public UserDto update(@PathVariable Long id, @RequestBody UserDto user) {
        user.setId(id);
        return service.save(user);
    }

    public void delete(Long id) {
        service.delete(id);
    }

}
