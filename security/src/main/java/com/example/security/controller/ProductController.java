package com.example.security.controller;

import com.example.security.entity.FireWord;
import com.example.security.entity.FireWordLogger;
import com.example.security.entity.Product;
import com.example.security.repository.FireWordLoggerRepository;
import com.example.security.repository.FireWordRepository;
import com.example.security.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private FireWordLoggerRepository fireWordLoggerRepository;
   @Autowired
    private FireWordRepository fireWordRepository;




    @Autowired
    ProductService productService;

    @GetMapping
    public String getProducts(){
        return "<h1>Welcome to products page</h1>";
    }

    @GetMapping("/new")
    public FireWord getProductsnew(){
        FireWord test = new FireWord("stupid");
        fireWordRepository.save(test);
        FireWordLogger fl = new FireWordLogger(1, LocalDateTime.now(),test);
        FireWordLogger f1 = new FireWordLogger(1, LocalDateTime.now(),test);
        FireWordLogger f2 = new FireWordLogger(1, LocalDateTime.now(),test);
        FireWordLogger f3 = new FireWordLogger(1, LocalDateTime.now(),test);
        FireWordLogger f4 = new FireWordLogger(1, LocalDateTime.now(),test);
        List<FireWordLogger> list = Arrays.asList(f1,f2,f3,f4,fl);
        fireWordLoggerRepository.saveAll(list);

        //return (List<FireWordLogger>) fireWordLoggerRepository.findAll();
        return fireWordRepository.findById(1).get();
    }
    @PostMapping
    public void saveProducts(@RequestBody Product product){
        productService.saveNewProduct(product);
    }
}
