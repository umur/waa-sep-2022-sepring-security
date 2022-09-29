package edu.miu.lab5.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class WaaOffensive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn
    private User user;

    private int wordsCount;

    private boolean isUnderBlocked;

    private Date bannedDateTime;

    private Date lastRequestDateTime;
}
