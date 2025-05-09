package com.greedy.leaderboard.exception;

import lombok.Getter;

@Getter
public class NotFoundUserException extends RuntimeException {

    private final String title;

    public NotFoundUserException(String title, String message) {
        super(message);
        this.title = title;
    }
}
