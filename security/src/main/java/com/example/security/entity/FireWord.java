package com.example.security.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@RedisHash("fireword")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FireWord {

    @Id
    @Indexed
    private String id;
    private String name;

    public FireWord(String name){
        this.name = name;
    }
}
