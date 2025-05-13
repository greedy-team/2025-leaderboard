package com.greedy.leaderboard.controller;

import com.greedy.leaderboard.dto.ScoreSubmitRequest;
import com.greedy.leaderboard.dto.ScoreSubmitResponse;
import com.greedy.leaderboard.dto.ScoreUpdateResponse;
import com.greedy.leaderboard.service.GameResultService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class GameResultController {

    private final GameResultService gameResultService;

    @PostMapping("/result")
    public ResponseEntity<ScoreSubmitResponse> writeGameResult(@Valid @RequestBody ScoreSubmitRequest gameScoreRequest) {
        ScoreSubmitResponse scoreSubmitResponse = gameResultService.writeGameResult(gameScoreRequest);
        if (scoreSubmitResponse.getStatus().equals("CREATED")) {
            return ResponseEntity.status(HttpStatus.CREATED).body(scoreSubmitResponse);
        }
        return ResponseEntity.ok().body(scoreSubmitResponse);
    }

    @PostMapping("/scores/force")
    public ResponseEntity<ScoreUpdateResponse> updateGameResult(@Valid @RequestBody ScoreSubmitRequest gameScoreRequest) {
        ScoreUpdateResponse scoreSubmitResponse = gameResultService.updateScore(gameScoreRequest);
        if (scoreSubmitResponse.getStatus().equals("CREATED")) {
            return ResponseEntity.status(HttpStatus.CREATED).body(scoreSubmitResponse);
        }
        return ResponseEntity.ok().body(scoreSubmitResponse);
    }

}
