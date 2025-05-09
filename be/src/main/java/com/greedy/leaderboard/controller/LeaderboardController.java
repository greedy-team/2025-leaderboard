package com.greedy.leaderboard.controller;

import com.greedy.leaderboard.dto.LeaderBoardResponse;
import com.greedy.leaderboard.service.LeaderboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/leader-board")
@RequiredArgsConstructor
public class LeaderboardController {

    private final LeaderboardService leaderboardService;

    @GetMapping("/overall")
    public ResponseEntity<LeaderBoardResponse> getOverallRanking() {
        return ResponseEntity.ok().body(leaderboardService.aggregateOverallRanking());
    }



}
