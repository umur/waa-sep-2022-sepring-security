package henry.springsecurity.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OffensiveWordsHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ElementCollection
    @CollectionTable(name = "offensive_words", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "offensive_words")
    private Set<String> offensiveWords;

    private LocalDateTime at;

    @ManyToOne
//    @JsonBackReference
    private User user;
}
