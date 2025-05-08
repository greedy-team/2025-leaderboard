package com.greedy.leaderboard.entity.game;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.greedy.leaderboard.exception.InvalidGameNameException;
import lombok.Getter;

@Getter
public enum Game {

    GREENY_NECK("greeny-neck", "그린이 목 늘이기"),
    KEYZZLE("keyzzle", "Keyzzle"),
    PIKACHU_VOLLEY("pikachu-volley", "피카츄 배구"),
    COURSE_REGISTRATION("course-registration", "수강신청 연습");

    private final String gameName;
    private final String gameNameKR;

    Game(String gameName, String gameNameKR) {
        this.gameName = gameName;
        this.gameNameKR = gameNameKR;
    }

    @JsonCreator
    public static Game from(String value) {
        for (Game game : Game.values()) {
            if (game.gameName.equals(value)) {
                return game;
            }
        }
        throw new InvalidGameNameException("유효하지 않은 게임 이름",
                "게임 이름은 greeny-neck, keyzzle, pikachu-volley, course-registration 중 하나여야 합니다. (입력값: " + value + ")");
    }
}
