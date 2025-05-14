package com.greedy.leaderboard.service;

import com.greedy.leaderboard.dto.ScoreSubmitRequest;
import com.greedy.leaderboard.dto.ScoreSubmitResponse;
import com.greedy.leaderboard.entity.User;
import com.greedy.leaderboard.entity.game.Game;
import com.greedy.leaderboard.exception.NotFoundUserException;
import com.greedy.leaderboard.repository.UserRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class GameResultServiceTest {
//
//    @Autowired
//    private GameResultService gameResultService;
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private EntityManager em;
//
//    private final String userId = "asdff";
//
//    @BeforeEach
//    void setUp() {
//        User user = new User(userId, "이창희", "010-0000-0000");
//        userRepository.save(user);
//    }
//
//    @Test
//    void 존재하지_않는_유저의_점수를_등록하면_예외가_발생해야_한다() {
//        ScoreSubmitRequest request = new ScoreSubmitRequest(Game.PIKACHU_VOLLEY, "qwerf", 20);
//        assertThatThrownBy(() -> gameResultService.writeGameResult(request))
//                .isInstanceOf(NotFoundUserException.class);
//    }
//
//    @Test
//    void 등록된_점수가_없다면_점수가_새롭게_등록되어야_한다() {
//        ScoreSubmitRequest request = new ScoreSubmitRequest(Game.KEYZZLE, userId, 8.95);
//        ScoreSubmitResponse scoreSubmitResponse = gameResultService.writeGameResult(request);
//        assertThat(scoreSubmitResponse.getStatus()).isEqualTo("CREATED");
//    }
//
//    @Test
//    void 등록된_점수가_있다면_점수가_기존보다_높을_때_갱신되어야_한다() {
//        ScoreSubmitRequest request1 = new ScoreSubmitRequest(Game.ALLCLL, userId, 10);
//        gameResultService.writeGameResult(request1);
//        em.flush();
//        em.clear();     // 영속성 컨텍스트 초기화
//
//        // 새로 등록
//        ScoreSubmitRequest request2 = new ScoreSubmitRequest(Game.ALLCLL, userId, 11);
//        ScoreSubmitResponse scoreSubmitResponse = gameResultService.writeGameResult(request2);
//        assertThat(scoreSubmitResponse.getStatus()).isEqualTo("UPDATED");
//    }
//
//    @Test
//    void 등록된_점수가_있다면_점수가_기존보다_낮다면_점수가_변경되면_안된다() {
//        ScoreSubmitRequest request1 = new ScoreSubmitRequest(Game.ALLCLL, userId, 10);
//        gameResultService.writeGameResult(request1);
//        em.flush();
//        em.clear();     // 영속성 컨텍스트 초기화
//
//        // 새로 등록
//        ScoreSubmitRequest request2 = new ScoreSubmitRequest(Game.ALLCLL, userId, 8);
//        ScoreSubmitResponse scoreSubmitResponse = gameResultService.writeGameResult(request2);
//        assertThat(scoreSubmitResponse.getStatus()).isEqualTo("UNCHANGED");
//    }
}
