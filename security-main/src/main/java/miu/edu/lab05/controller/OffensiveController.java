package miu.edu.lab05.controller;

import miu.edu.lab05.aspect.WaaWord;
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
