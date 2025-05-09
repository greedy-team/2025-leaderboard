package com.greedy.leaderboard.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity @Getter
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id
    @Column(name = "user_id", nullable = false, unique = true)
    private String userId;

    @Column(name = "nickname", nullable = false, unique = true)
    private String nickname;

    @Column(name = "phone", unique = true)
    private String phone;

    public User(String userId, String nickname, String phone) {
        this.userId = userId;
        this.nickname = nickname;
        this.phone = phone;
    }
}
