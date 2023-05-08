package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "movieTitle")
    private String movieTitle;

    @Column(name = "rating")
    private String rating;

    @Column(name = "review")
    private String review;

    @Column(name = "userId")
    private String userId;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;
}
