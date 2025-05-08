package com.greedy.leaderboard.dto;

import com.greedy.leaderboard.entity.game.Game;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ScoreSubmitResponse {

    private String status;
    private String message;
    private String gameName;

    public ScoreSubmitResponse(SubmitStatus submitStatus, Game game) {
        this.status = submitStatus.name();
        this.message = submitStatus.getMessage();
        this.gameName = game.getGameNameKR();
    }
}
