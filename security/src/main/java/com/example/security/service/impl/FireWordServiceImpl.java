package com.example.security.service.impl;

import com.example.security.entity.FireWord;
import com.example.security.repository.FireWordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FireWordServiceImpl {

    @Autowired
    FireWordRepository fireWordRepository;


    public boolean checkOffensiveWord(String input){
        FireWord test = new FireWord("stupid");
        List<FireWord> fireWordList = Arrays.asList(test);
        if(fireWordList.stream().filter(fireWord -> fireWord.getName().startsWith(test.getName())).collect(Collectors.toList()).size() >0)
            return true;
        return false;
    }
}
