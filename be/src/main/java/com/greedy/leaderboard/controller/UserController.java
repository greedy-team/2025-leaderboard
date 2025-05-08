package com.greedy.leaderboard.controller;

import com.greedy.leaderboard.dto.UserProfileRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    @PostMapping("/users")
    public ResponseEntity<Void> create(@Valid @RequestBody UserProfileRequest requestDto) {
        return ResponseEntity.ok().build();
    }
}
