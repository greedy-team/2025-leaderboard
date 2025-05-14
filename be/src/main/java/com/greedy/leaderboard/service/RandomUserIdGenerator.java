package com.greedy.leaderboard.service;

import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
public class RandomUserIdGenerator implements IdGenerator {

    private static final String BASE_CHARACTERS = "ABCDEGHJMNPQRSTUVWXYabdeghjmnqrty23456789";
    private static final int USER_ID_LENGTH = 4;
    private final ThreadLocalRandom random = ThreadLocalRandom.current();

    @Override
    public String generateId() {
        StringBuilder userId = new StringBuilder(USER_ID_LENGTH);
        for (int i = 0; i < USER_ID_LENGTH; i++) {
            userId.append(BASE_CHARACTERS.charAt(random.nextInt(BASE_CHARACTERS.length())));
        }
        return userId.toString();
    }
}
