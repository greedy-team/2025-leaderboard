package com.greedy.leaderboard.dto;


import com.greedy.leaderboard.entity.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserProfileResponse {

    private String userId;
    private String nickname;
    private String phone;

    public UserProfileResponse(User user) {
        this.userId = user.getUserId();
        this.nickname = user.getNickname();
        this.phone = user.getPhone();
    }
}
