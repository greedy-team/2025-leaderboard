package com.greedy.leaderboard.entity.game;

import com.greedy.leaderboard.entity.BaseEntity;
import com.greedy.leaderboard.entity.User;
import jakarta.persistence.*;
import lombok.Getter;

@Entity @Getter
@Table(name = "pikachu_volley")
public class PikachuVolley extends BaseEntity implements GameEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double score;      // 점수

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", unique = true)
    private User user;


    public void submitScore(double score, User user) {
        this.score = score;
        this.user = user;
    }

    public void updateScore(double score) {
        this.score = score;
    }

    @Override
    public double getScore() {
        return this.score;
    }

    @Override
    public String getNickname() {
        return this.user.getNickname();
    }
}
