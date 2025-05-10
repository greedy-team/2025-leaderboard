package com.greedy.leaderboard.exception;

import lombok.Getter;

@Getter
public class DuplicateNicknameException extends RuntimeException {

    private final String title;

    public DuplicateNicknameException(String title, String message) {
        super(message);
        this.title = title;
    }
}
