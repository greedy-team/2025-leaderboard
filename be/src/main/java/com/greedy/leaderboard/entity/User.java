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
    @Column(name = "user_id")
    private String id;
    private String nickname;
    private String phone;

    public User(String id, String nickname, String phone) {
        this.id = id;
        this.nickname = nickname;
        this.phone = phone;
    }
}
