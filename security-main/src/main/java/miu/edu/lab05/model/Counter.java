package miu.edu.lab05.model;

import lombok.Data;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Data

public class Counter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String content;
    private Long userId;
    private Instant createdAt;

    public Counter(String content, Long userId) {
        this.content = content;
        this.userId = userId;
        this.createdAt = Instant.now();
    }

    public Counter() {

    }
}
