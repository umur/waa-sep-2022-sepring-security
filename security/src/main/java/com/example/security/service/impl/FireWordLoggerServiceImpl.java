package com.example.security.service.impl;

import com.example.security.entity.FireWordLogger;
import com.example.security.repository.FireWordLoggerRepository;
import com.example.security.service.FireWordLoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FireWordLoggerServiceImpl implements FireWordLoggerService {

    @Autowired
    FireWordLoggerRepository fireWordLoggerRepository;

    public List<FireWordLogger> getListOfUser(int id) {
        List<FireWordLogger> fl = (List<FireWordLogger>) fireWordLoggerRepository.findAll();
        return fl.stream().filter(fireWordLogger -> fireWordLogger.getUserId() == id).collect(Collectors.toList());
    }
}