package com.greedy.leaderboard.dto;

import lombok.Getter;

@Getter
public enum SubmitStatus {

    UPDATED("점수가 갱신되었습니다"),
    CREATED("점수가 등록되었습니다"),
    UNCHANGED("기존 기록보다 높은 점수에 도달하지 못해 점수가 갱신되지 않았습니다.");

    private final String message;

    SubmitStatus(String message) {
        this.message = message;
    }
}
