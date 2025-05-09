package com.greedy.leaderboard.entity.game;

import com.greedy.leaderboard.entity.BaseEntity;
import com.greedy.leaderboard.entity.User;
import jakarta.persistence.*;
import lombok.Getter;

@Entity @Getter
@Table(name = "pikachu_volley")
public class PikachuVolley extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int score;      // 점수

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", unique = true)
    private User user;


    public void submitScore(double score, User user) {
        this.score = (int) score;
        this.user = user;
    }

    public void updateScore(double score) {
        this.score = (int) score;
    }
}
