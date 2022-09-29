package miu.edu.lab05.controller;

import lombok.RequiredArgsConstructor;
import miu.edu.lab05.service.UaaServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping("api/uaa")
@RequiredArgsConstructor
@CrossOrigin
public class UaaController {

    private final UaaServiceImpl service;

    @PostMapping("signin")
    public Map<String, String> signIn(@RequestBody Map<String, String> body) {
        return service.login(body);
    }

    @GetMapping("validate")
    public Map<String, Boolean> validate() {
        return service.validate();
    }

    @DeleteMapping("signout")
    public void signOut(Principal principal) {

    }
}
