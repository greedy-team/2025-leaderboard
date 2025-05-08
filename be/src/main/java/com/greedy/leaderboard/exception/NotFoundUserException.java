package com.greedy.leaderboard.exception;

public class NotFoundUserException extends RuntimeException {

    private String title;

    public NotFoundUserException(String title, String message) {
        super(message);
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
