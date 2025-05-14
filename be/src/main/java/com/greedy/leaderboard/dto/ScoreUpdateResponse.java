package com.greedy.leaderboard.dto;

import com.greedy.leaderboard.entity.game.Game;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ScoreUpdateResponse {

    private String status;
    private String message;
    private String gameName;
    private double previousScore;
    private double updateScore;

    public ScoreUpdateResponse(SubmitStatus submitStatus, Game game, double previousScore, double updateScore) {
        this.status = submitStatus.name();
        this.message = submitStatus.getMessage();
        this.gameName = game.getGameNameKR();
        this.previousScore = previousScore;
        this.updateScore = updateScore;
    }
}
