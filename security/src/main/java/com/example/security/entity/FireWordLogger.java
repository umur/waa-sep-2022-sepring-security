package com.example.security.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@RedisHash("firewordhistory")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class FireWordLogger {

    @Id
    @Indexed
    private String id;
    private int userId;
    private LocalDateTime time;
    @OneToOne
    private FireWord fireWord;

    public FireWordLogger(int userId,LocalDateTime time,FireWord fireWord){
        this.fireWord=fireWord;
        this.id = id;
        this.userId =userId;
        this.time = time;
    }

}
