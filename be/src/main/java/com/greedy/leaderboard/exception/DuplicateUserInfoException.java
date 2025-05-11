package com.greedy.leaderboard.exception;

import lombok.Getter;

@Getter
public class DuplicateUserInfoException extends RuntimeException {

    private final String title;

    public DuplicateUserInfoException(String title, String message) {
        super(message);
        this.title = title;
    }
}
