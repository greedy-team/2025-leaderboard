package com.greedy.leaderboard.service;

import com.greedy.leaderboard.dto.LeaderBoardResponse;
import com.greedy.leaderboard.dto.LeaderboardRanking;
import com.greedy.leaderboard.dto.ScoreSubmitRequest;
import com.greedy.leaderboard.dto.UserProfileRequest;
import com.greedy.leaderboard.entity.User;
import com.greedy.leaderboard.entity.game.Game;
import com.greedy.leaderboard.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
@Transactional
class LeaderboardServiceTest {

    @Autowired
    private LeaderboardService leaderboardService;
    @Autowired
    private GameResultService gameResultService;
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        // 유저 8명 등록
        userRepository.save(new User("0000", "이승용", "010-0000-0001"));
        userRepository.save(new User("0001", "김주환", "010-0000-0002"));
        userRepository.save(new User("0002", "김지우", "010-0000-0003"));
        userRepository.save(new User("0003", "이창희", "010-0000-0004"));
        userRepository.save(new User("0004", "허석준", "010-0000-0005"));
        userRepository.save(new User("0005", "황혜림", "010-0000-0006"));
        userRepository.save(new User("0006", "전서희", "010-0000-0007"));
        userRepository.save(new User("0007", "염지환", "010-0000-0008"));
    }

    @Test
    void 공동_일등이_4명_발생하면_다음순위는_5등이다() {
        // 각 1등
        gameResultService.writeGameResult(new ScoreSubmitRequest(Game.ALLCLL, "0001", 100));
        gameResultService.writeGameResult(new ScoreSubmitRequest(Game.KEYZZLE, "0002", 100));
        gameResultService.writeGameResult(new ScoreSubmitRequest(Game.GREENY_NECK, "0003", 100));
        gameResultService.writeGameResult(new ScoreSubmitRequest(Game.PIKACHU_VOLLEY, "0004", 100));

        // 5위
        gameResultService.writeGameResult(new ScoreSubmitRequest(Game.ALLCLL, "0005", 10));

        List<LeaderboardRanking> rankings = leaderboardService.aggregateOverallRanking().getRankings();

        assertThat(rankings.size()).isEqualTo(5);
        assertThat(rankings)
                .extracting(LeaderboardRanking::getRank)
                .containsExactly(1,1,1,1,5);
    }

    @Test
    void 모든_게임에서_1등을_하면_최종_우슨자의_점수는_40점이다() {
        // 각 1등
        gameResultService.writeGameResult(new ScoreSubmitRequest(Game.ALLCLL, "0000", 100));
        gameResultService.writeGameResult(new ScoreSubmitRequest(Game.KEYZZLE, "0000", 100));
        gameResultService.writeGameResult(new ScoreSubmitRequest(Game.GREENY_NECK, "0000", 100));
        gameResultService.writeGameResult(new ScoreSubmitRequest(Game.PIKACHU_VOLLEY, "0000", 100));

        gameResultService.writeGameResult(new ScoreSubmitRequest(Game.ALLCLL, "0001", 100));        // 올클 공동우승
        gameResultService.writeGameResult(new ScoreSubmitRequest(Game.KEYZZLE, "0002", 110));       // 키즐은 점수가 낮은사람이 높은 순위
        gameResultService.writeGameResult(new ScoreSubmitRequest(Game.GREENY_NECK, "0003", 110));   // 그린이는 점수가 낮은사람이 높은 순위
        gameResultService.writeGameResult(new ScoreSubmitRequest(Game.PIKACHU_VOLLEY, "0004", 90));
        // 5위
        gameResultService.writeGameResult(new ScoreSubmitRequest(Game.ALLCLL, "0003", 10));

        gameResultService.writeGameResult(new ScoreSubmitRequest(Game.ALLCLL, "0007", 13));

        List<LeaderboardRanking> rankings = leaderboardService.aggregateOverallRanking().getRankings();

        assertThat(rankings.size()).isEqualTo(5);
        assertThat(rankings.get(0).getNickname()).isEqualTo("이승용");
        assertThat(rankings.get(0).getScore()).isEqualTo(40);
        assertThat(rankings)
                .extracting(LeaderboardRanking::getRank)
                .containsExactly(1,2,3,4,4);
        assertThat(rankings)
                .extracting(LeaderboardRanking::getScore)
                .containsExactly(40,11,10,7,7);
    }


    // 랭킹 체크용 로그 메서드
    private static void overallRankingLog(List<LeaderboardRanking> rankings) {
        for (LeaderboardRanking ranking : rankings) {
            System.out.println(ranking.getRank()+ " " + ranking.getScore() + " " + ranking.getNickname());
        }
    }

}
