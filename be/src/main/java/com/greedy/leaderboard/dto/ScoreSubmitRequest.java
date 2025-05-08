package com.greedy.leaderboard.dto;

import com.greedy.leaderboard.entity.game.Game;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ScoreSubmitRequest {

    @NotNull(message = "게임 이름은 null이면 안됩니다")
    private Game gameName;

    @NotBlank(message = "유저 식별 정보는 필수입니다")
    private String userId;

    @Min(value = 0, message = "점수는 0 이상이여야 합니다")
    private double score;
}
