package com.greedy.leaderboard.service;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum RankingPoint {
    // 부여되는 점수에서 3점을 제외 (3점은 기본 점수)

    FIRST(1, 7),
    SECOND(2, 4),
    THIRD(3, 2),
    FOURTH(4, 1);

    private final int rank;
    private final int points;

    public static int getPointByRank(int rank) {
        return Arrays.stream(values())
                .filter(r -> r.rank == rank)
                .findFirst()
                .map(RankingPoint::getPoints)
                .orElse(0);
    }
}
