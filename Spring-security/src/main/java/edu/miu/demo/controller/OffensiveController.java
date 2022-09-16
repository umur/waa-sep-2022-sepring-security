package edu.miu.demo.controller;

import edu.miu.demo.aspect.WaaWord;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/offensive")
@CrossOrigin
public class OffensiveController {

    @PostMapping("check")
    @WaaWord
    public List<String> checkOffensiveWords(@RequestBody List<String> words) {
        return words;
    }
}
