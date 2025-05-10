package com.greedy.leaderboard.service;

import com.greedy.leaderboard.dto.GameRankingResponse;
import com.greedy.leaderboard.dto.LeaderBoardResponse;
import com.greedy.leaderboard.dto.LeaderboardRanking;
import com.greedy.leaderboard.entity.game.Game;
import com.greedy.leaderboard.entity.game.GameEntity;
import com.greedy.leaderboard.exception.InvalidGameNameException;
import com.greedy.leaderboard.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LeaderboardService {

    private final PikachuVolleyRepository pikachuVolleyRepository;
    private final KeyzzleRepository keyzzleRepository;
    private final GreenyNeckRepository greenyNeckRepository;
    private final AllcllRepository allcllRepository;


    public LeaderBoardResponse aggregateOverallRanking() {
        Map<String, Integer> scoreMap = new HashMap<>();
        addBasePointsPerGames(scoreMap);
        addRankingPointsPerGames(scoreMap);

        return new LeaderBoardResponse(getTop5Rankings(scoreMap));
    }

    // 게임에 참가한 모든 사람들 기본점수 3점 부여
    private void addBasePointsPerGames(Map<String, Integer> scoreMap) {
        pikachuVolleyRepository.findAllWithUser().forEach(
                p -> scoreMap.merge(p.getUser().getNickname(), 3, Integer::sum)
        );
        allcllRepository.findAllWithUser().forEach(
                a -> scoreMap.merge(a.getUser().getNickname(), 3, Integer::sum)
        );
        keyzzleRepository.findAllWithUser().forEach(
                k -> scoreMap.merge(k.getUser().getNickname(), 3, Integer::sum)
        );
        greenyNeckRepository.findAllWithUser().forEach(
                g -> scoreMap.merge(g.getUser().getNickname(), 3, Integer::sum)
        );
    }

    // 각 게임의 순위권 참가자 점수 부여 (1위 ~ 4위)
    private void addRankingPointsPerGames(Map<String, Integer> scoreMap) {
        pikachuVolleyRepository.findTop5WithRank().forEach(
                p -> scoreMap.merge(p.getNickname(), RankingPoint.getPointByRank(p.getRank()), Integer::sum)
        );
        keyzzleRepository.findTop5WithRank().forEach(
                p -> scoreMap.merge(p.getNickname(), RankingPoint.getPointByRank(p.getRank()), Integer::sum)
        );
        greenyNeckRepository.findTop5WithRank().forEach(
                p -> scoreMap.merge(p.getNickname(), RankingPoint.getPointByRank(p.getRank()), Integer::sum)
        );
        allcllRepository.findTop5WithRank().forEach(
                p -> scoreMap.merge(p.getNickname(), RankingPoint.getPointByRank(p.getRank()), Integer::sum)
        );
    }

    // 랭킹 계산 (동점자 발생시 동점자 - 1 만큼 순위를 pass)
    private List<LeaderboardRanking> getTop5Rankings(Map<String, Integer> scoreMap) {
        // 1. 점수 높은순으로 정렬
        List<Map.Entry<String, Integer>> sortedList = scoreMap.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .toList();

        List<LeaderboardRanking> result = new ArrayList<>();
        int currentRank = 0;
        int actualIndex = 0;
        Integer prevScore = null;

        for (Map.Entry<String, Integer> entry : sortedList) {
            actualIndex++;

            if (!Objects.equals(prevScore, entry.getValue())) {
                currentRank = actualIndex;
            }

            if (currentRank > 5) break;

            result.add(new LeaderboardRanking(currentRank, entry.getKey(), entry.getValue()));
            prevScore = entry.getValue();
        }

        return result;
    }

    public GameRankingResponse getSingleGameRankings(String gameName) {
        Game game = Game.from(gameName);

        List<GameRankingResponse.Ranking> rankings;

        switch (game) {
            case ALLCLL -> rankings = processRanking(allcllRepository.findTop5WithRank());
            case GREENY_NECK -> rankings = processRanking(greenyNeckRepository.findTop5WithRank());
            case KEYZZLE -> rankings = processRanking(keyzzleRepository.findTop5WithRank());
            case PIKACHU_VOLLEY -> rankings = processRanking(pikachuVolleyRepository.findTop5WithRank());
            default -> throw new InvalidGameNameException("게임 이름 오류", "올바른 게임 이름을 입력해주세요.");
        }

        return new GameRankingResponse(game.getGameNameKR(), rankings);
    }

    private <T extends GameEntity> List<GameRankingResponse.Ranking> processRanking(List<RankQueryInterface> entities) {
        List<GameRankingResponse.Ranking> rankings = new ArrayList<>();

        int currentRank = 1;
        double previousScore = -1;
        int sameRankCount = 0;

        for (int i = 0; i < entities.size(); i++) {
            var entity = entities.get(i);

            double score = entity.getScore();
            String nickname = entity.getNickname();

            if (score != previousScore) {
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
