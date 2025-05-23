package com.greedy.leaderboard.exception;

import lombok.Getter;

@Getter
public class InvalidGameNameException extends RuntimeException {

    private final String title;

    public InvalidGameNameException(String title, String message) {
        super(message);
        this.title = title;
    }
}
