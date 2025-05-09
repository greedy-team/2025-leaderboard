package com.greedy.leaderboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Data
@AllArgsConstructor
public class GameRankingResponse {

    private String gameName;
    private List<Ranking> rankings;

    @Data
    @AllArgsConstructor
    public static class Ranking {
        private int rank;
        private String nickname;
        private double score;
    }
}
