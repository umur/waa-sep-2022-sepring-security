package henry.springsecurity.controller;

import henry.springsecurity.dto.UserDto;
import henry.springsecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@CrossOrigin
@RestController
@Slf4j
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    public void save(@RequestBody UserDto userDto) {
        userService.save(userDto);
    }

    @GetMapping
    public List<UserDto> findAll() {
        log.debug("User find all");
        return userService.findAll();
    }

    @PutMapping("/{userId}")
    public void update(@PathVariable int userId, @RequestBody UserDto userDto) {
        userService.update(userId, userDto);
    }

    @DeleteMapping("/{userId}")
    public UserDto delete(@PathVariable int userId) {
        return userService.delete(userId);
    }
}
