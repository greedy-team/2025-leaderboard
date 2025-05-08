package com.greedy.leaderboard.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResultResponse {
    private final String title;
    private final int status;
    private final String detail;
    private final String instance;
}
