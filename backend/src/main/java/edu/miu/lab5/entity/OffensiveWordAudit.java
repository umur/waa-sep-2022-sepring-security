package edu.miu.lab5.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OffensiveWordAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ElementCollection
    @CollectionTable(name = "offensive_word", joinColumns = @JoinColumn(name = "id"))
    private Set<String> offensiveWords;

    private LocalDateTime createdAt;

    @ManyToOne
    @JsonBackReference
    private User user;
}
