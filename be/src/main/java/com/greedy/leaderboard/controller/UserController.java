package com.greedy.leaderboard.controller;

import com.greedy.leaderboard.dto.UserProfileRequest;
import com.greedy.leaderboard.dto.UserProfileResponse;
import com.greedy.leaderboard.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    public ResponseEntity<UserProfileResponse> createUser(@Valid @RequestBody UserProfileRequest userProfileRequest) {
        UserProfileResponse userProfileResponse = userService.createUser(userProfileRequest);

        return ResponseEntity.ok().body(userProfileResponse);
    }
}
