package com.greedy.leaderboard.exception;

public class InvalidGameNameException extends RuntimeException {

    private String title;

    public InvalidGameNameException(String title, String message) {
        super(message);
        this.title = title;
    }

    public String getTitle() {
      return title;
    }
}
