package com.greedy.leaderboard.service;

import com.greedy.leaderboard.dto.ScoreSubmitRequest;
import com.greedy.leaderboard.dto.ScoreSubmitResponse;
import com.greedy.leaderboard.dto.ScoreUpdateResponse;
import com.greedy.leaderboard.dto.SubmitStatus;
import com.greedy.leaderboard.entity.User;
import com.greedy.leaderboard.entity.game.*;
import com.greedy.leaderboard.exception.InvalidGameNameException;
import com.greedy.leaderboard.exception.NotFoundUserException;
import com.greedy.leaderboard.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class GameResultService {

    private final UserRepository userRepository;
    private final PikachuVolleyRepository pikachuVolleyRepository;
    private final KeyzzleRepository keyzzleRepository;
    private final GreenyNeckRepository greenyNeckRepository;
    private final AllcllRepository allcllRepository;

    @Transactional
    public ScoreSubmitResponse writeGameResult(ScoreSubmitRequest request) {

        // 1. 현재 유저가 등록이 되어 있는제 체크 (입력된 유저 유효성 체크)
        User findUser = getUserByUserId(request.getUserId());

        // 2. 유저가 해당 게임을 해 보았는지 처크 -> 해본적 없다 Create / 있다 -> 점수 update
        Game game = request.getGameName();
        SubmitStatus submitStatus;
        switch (game) {
            case KEYZZLE:
                submitStatus = upsertKeyzzle(request, findUser);
                break;
            case GREENY_NECK:
                submitStatus = upsertGreenyNeck(request, findUser);
                break;
            case  PIKACHU_VOLLEY:
                submitStatus = upsertPikachuVolley(request, findUser);
                break;
            case ALLCLL:
                submitStatus = upsertAllcll(request, findUser);
                break;
            default:
                throw new InvalidGameNameException("존재하지 않는 게임", "게임은 greeny-neck, keyzzle, pikachu-volley, allcll 중 하나여야 합니다.");
        }
        return new ScoreSubmitResponse(submitStatus, game);
    }


    private SubmitStatus upsertAllcll(ScoreSubmitRequest request, User user) {
        Optional<Allcll> courseScoreByUser = allcllRepository.findByUser_UserId(user.getUserId());
        if (courseScoreByUser.isPresent()) {
            Allcll findCourseScore = courseScoreByUser.get();
            if (request.getScore() > findCourseScore.getScore()) {
                findCourseScore.updateScore(request.getScore());
                return SubmitStatus.UPDATED;
            }
            return SubmitStatus.UNCHANGED;
        }
        Allcll allcll = new Allcll();
        allcll.submitScore(request.getScore(), user);
        allcllRepository.save(allcll);
        return SubmitStatus.CREATED;
    }

    private SubmitStatus upsertPikachuVolley(ScoreSubmitRequest request, User findUser) {
        Optional<PikachuVolley> pikachuScoreByUser = pikachuVolleyRepository.findByUser_UserId(findUser.getUserId());
        if (pikachuScoreByUser.isPresent()) {
            PikachuVolley findPikachuScore = pikachuScoreByUser.get();
            if (request.getScore() > findPikachuScore.getScore()) {      // 새 점수가 높으면 업데이트 (점수)
                findPikachuScore.updateScore(request.getScore());
                return SubmitStatus.UPDATED;
            }
            return SubmitStatus.UNCHANGED;
        }
        PikachuVolley pikachuVolley = new PikachuVolley();
        pikachuVolley.submitScore(request.getScore(), findUser);
        pikachuVolleyRepository.save(pikachuVolley);
        return SubmitStatus.CREATED;
    }

    private SubmitStatus upsertGreenyNeck(ScoreSubmitRequest request, User findUser) {
        Optional<GreenyNeck> greenyScoreByUser = greenyNeckRepository.findByUser_UserId(findUser.getUserId());
        if (greenyScoreByUser.isPresent()) {
            GreenyNeck findGreenyNeck = greenyScoreByUser.get();
            if (request.getScore() < findGreenyNeck.getScore()) {       // 새 점수가 낮으면 업데이트 (시간)
                findGreenyNeck.updateScore(request.getScore());
                return SubmitStatus.UPDATED;
            }
            return SubmitStatus.UNCHANGED;
        }
        GreenyNeck greenyNeck = new GreenyNeck();
        greenyNeck.submitScore(request.getScore(), findUser);
        greenyNeckRepository.save(greenyNeck);
        return SubmitStatus.CREATED;
    }

    private SubmitStatus upsertKeyzzle(ScoreSubmitRequest request, User findUser) {
        Optional<Keyzzle> keyzzleScoreByUser = keyzzleRepository.findByUser_UserId(findUser.getUserId());
        if (keyzzleScoreByUser.isPresent()) {
            Keyzzle findKeyzzleScore = keyzzleScoreByUser.get();
            if (request.getScore() < findKeyzzleScore.getScore()) {       // 새 점수가 낮으면 업데이트 (시간)
                findKeyzzleScore.updateScore(request.getScore());
                return SubmitStatus.UPDATED;
            }
            return SubmitStatus.UNCHANGED;
        }
        Keyzzle keyzzle = new Keyzzle();
        keyzzle.submitScore(request.getScore(), findUser);
        keyzzleRepository.save(keyzzle);
        return SubmitStatus.CREATED;
    }

    private User getUserByUserId(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundUserException("등록되지 않은 유저", "아이디가 " + userId + "인 유저는 존재하지 않습니다."));
    }

    // DB 조작용 업데이트
    @Transactional
    public ScoreUpdateResponse updateScore(ScoreSubmitRequest request) {
        // 1. 현재 유저가 등록이 되어 있는제 체크 (입력된 유저 유효성 체크)
        User findUser = getUserByUserId(request.getUserId());

        // 2. 유저가 해당 게임을 해 보았는지 체크 -> 해본적 없다 Create / 있다 -> 점수 update
        Game game = request.getGameName();
        Pair<SubmitStatus, Double> submitStatus;
        switch (game) {
            case KEYZZLE:
                submitStatus = updateKeyzzle(request, findUser);
                break;
            case GREENY_NECK:
                submitStatus = updateGreenyNeck(request, findUser);
                break;
            case  PIKACHU_VOLLEY:
                submitStatus = updatePikachuVolley(request, findUser);
                break;
            case ALLCLL:
                submitStatus = updateAllcll(request, findUser);
                break;
            default:
                throw new InvalidGameNameException("존재하지 않는 게임", "게임은 greeny-neck, keyzzle, pikachu-volley, allcll 중 하나여야 합니다.");
        }
        return new ScoreUpdateResponse(submitStatus.getFirst(), game, submitStatus.getSecond(), request.getScore());
    }

    private Pair<SubmitStatus, Double> updateAllcll(ScoreSubmitRequest request, User user) {
        Optional<Allcll> courseScoreByUser = allcllRepository.findByUser_UserId(user.getUserId());
        if (courseScoreByUser.isPresent()) {
            Allcll findCourseScore = courseScoreByUser.get();
            double score = findCourseScore.getScore();
            findCourseScore.updateScore(request.getScore());
            return Pair.of(SubmitStatus.UPDATED, score);
        }
        Allcll allcll = new Allcll();
        allcll.submitScore(request.getScore(), user);
        allcllRepository.save(allcll);
        return Pair.of(SubmitStatus.CREATED, 0.0);
    }

    private Pair<SubmitStatus, Double> updatePikachuVolley(ScoreSubmitRequest request, User findUser) {
        Optional<PikachuVolley> pikachuScoreByUser = pikachuVolleyRepository.findByUser_UserId(findUser.getUserId());
        if (pikachuScoreByUser.isPresent()) {
            PikachuVolley findPikachuScore = pikachuScoreByUser.get();
            double score = findPikachuScore.getScore();
            findPikachuScore.updateScore(request.getScore());
            return Pair.of(SubmitStatus.UPDATED, score);
        }
        PikachuVolley pikachuVolley = new PikachuVolley();
        pikachuVolley.submitScore(request.getScore(), findUser);
        pikachuVolleyRepository.save(pikachuVolley);
        return Pair.of(SubmitStatus.CREATED, 0.0);
    }

    private Pair<SubmitStatus, Double> updateGreenyNeck(ScoreSubmitRequest request, User findUser) {
        Optional<GreenyNeck> greenyScoreByUser = greenyNeckRepository.findByUser_UserId(findUser.getUserId());
        if (greenyScoreByUser.isPresent()) {
            GreenyNeck findGreenyNeck = greenyScoreByUser.get();
            double score = findGreenyNeck.getScore();
            findGreenyNeck.updateScore(request.getScore());
            return Pair.of(SubmitStatus.UPDATED, score);
        }
        GreenyNeck greenyNeck = new GreenyNeck();
        greenyNeck.submitScore(request.getScore(), findUser);
        greenyNeckRepository.save(greenyNeck);
        return Pair.of(SubmitStatus.CREATED, 0.0);
    }

    private Pair<SubmitStatus, Double> updateKeyzzle(ScoreSubmitRequest request, User findUser) {
        Optional<Keyzzle> keyzzleScoreByUser = keyzzleRepository.findByUser_UserId(findUser.getUserId());
        if (keyzzleScoreByUser.isPresent()) {
            Keyzzle findKeyzzleScore = keyzzleScoreByUser.get();
            double score = findKeyzzleScore.getScore();
            findKeyzzleScore.updateScore(request.getScore());
            return Pair.of(SubmitStatus.UPDATED, score);
        }
        Keyzzle keyzzle = new Keyzzle();
        keyzzle.submitScore(request.getScore(), findUser);
        keyzzleRepository.save(keyzzle);
        return Pair.of(SubmitStatus.CREATED, 0.0);
    }

}
