package com.greedy.leaderboard.entity.game;

import com.greedy.leaderboard.entity.BaseEntity;
import com.greedy.leaderboard.entity.User;
import jakarta.persistence.*;
import lombok.Getter;

@Entity @Getter
@Table(name = "course_registration")
public class CourseRegistration extends BaseEntity implements GameEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    public void submitScore(double score, User user) {
        this.score = (int) score;
        this.user = user;
    }

    public void updateScore(double score) {
        this.score = (int) score;
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
