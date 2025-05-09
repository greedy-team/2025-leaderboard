package com.greedy.leaderboard.service;

import com.greedy.leaderboard.dto.GameRankingResponse;
import com.greedy.leaderboard.entity.game.*;
import com.greedy.leaderboard.exception.InvalidGameNameException;
import com.greedy.leaderboard.repository.CourseRegistrationRepository;
import com.greedy.leaderboard.repository.GreenyNeckRepository;
import com.greedy.leaderboard.repository.KeyzzleRepository;
import com.greedy.leaderboard.repository.PikachuVolleyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LeaderboardService {

    private final CourseRegistrationRepository courseRegistrationRepository;
    private final GreenyNeckRepository greenyNeckRepository;
    private final KeyzzleRepository keyzzleRepository;
    private final PikachuVolleyRepository pikachuVolleyRepository;

    public GameRankingResponse getgGameRankings(String gameName) {
        Game game = Game.from(gameName);

        List<GameRankingResponse.Ranking> rankings;

        switch (game) {
            case COURSE_REGISTRATION -> rankings = processRanking(courseRegistrationRepository.findTop5WithTies());
            case GREENY_NECK -> rankings = processRanking(greenyNeckRepository.findTop5WithTies());
            case KEYZZLE -> rankings = processRanking(keyzzleRepository.findTop5WithTies());
            case PIKACHU_VOLLEY -> rankings = processRanking(pikachuVolleyRepository.findTop5WithTies());
            default -> throw new InvalidGameNameException("게임 이름 오류", "올바른 게임 이름을 입력해주세요.");
        }

        return new GameRankingResponse(game.getGameNameKR(), rankings);
    }

    private <T extends GameEntity> List<GameRankingResponse.Ranking> processRanking(List<T> entities) {
        List<GameRankingResponse.Ranking> rankings = new ArrayList<>();

        int currentRank = 1;     // 현재 순위
        double previousScore = -1;  // 이전 순위 점수
        int sameRankCount = 0;   // 동점자 수

        for (int i = 0; i < entities.size(); i++) {
            var entity = entities.get(i);

            double score = entity.getScore();
            String nickname = entity.getNickname();

            if (score != previousScore) { //현재 점수가 이전 순위 점수와 다름
                currentRank += sameRankCount;
                sameRankCount = 1;
            } else {
                sameRankCount++;
            }

            previousScore = score;

            rankings.add(new GameRankingResponse.Ranking(currentRank, nickname, score));
        }

        return rankings;
    }
}
