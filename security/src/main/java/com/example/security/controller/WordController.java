package com.example.security.controller;

import com.example.security.aspect.Loggable;
import com.example.security.entity.FireWordLogger;
import com.example.security.repository.FireWordLoggerRepository;
import com.example.security.service.impl.FireWordLoggerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("word")
public class WordController {

    @Autowired
    FireWordLoggerRepository fireWordLoggerRepository;

    @Autowired
    FireWordLoggerServiceImpl fireWordLoggerService;
    @PostMapping
    @Loggable
    public String checkOffensiveWords(@RequestBody String input){

        System.out.println("here");
    return "";
    }

    @GetMapping
    public List<FireWordLogger> getAll(){
        return (List<FireWordLogger>)fireWordLoggerRepository.findAll();
    }
    @DeleteMapping
    public void deleteAll(){
        fireWordLoggerRepository.deleteAll();
    }
}
