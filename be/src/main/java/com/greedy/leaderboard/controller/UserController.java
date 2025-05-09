package com.greedy.leaderboard.controller;

import com.greedy.leaderboard.dto.UserProfileRequest;
import com.greedy.leaderboard.dto.UserProfileResponse;
import com.greedy.leaderboard.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserProfileResponse> create(@Valid @RequestBody UserProfileRequest requestDto) {
        UserProfileResponse user = userService.createUser(requestDto);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserProfileResponse> getUserInfo(@PathVariable(name = "userId") String userId) {
        return ResponseEntity.ok().body(userService.getUserById(userId));
    }
}
