package com.example.springsecurity.Model;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String comment;
    @JoinColumn(name="user_id")
    @ManyToOne
    private AppUser user;
}
