package com.greedy.leaderboard.controller;

import com.greedy.leaderboard.dto.GameRankingResponse;
import com.greedy.leaderboard.service.LeaderboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/leaderboard")
@RequiredArgsConstructor
public class LeaderboardController {

    private final LeaderboardService leaderboardService;

    @GetMapping("/{gameName}")
    public GameRankingResponse getGameRankings(@PathVariable String gameName) {
        return leaderboardService.getgGameRankings(gameName);
    }
}
