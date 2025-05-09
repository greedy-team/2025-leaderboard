package com.greedy.leaderboard.service;

import com.greedy.leaderboard.dto.UserProfileRequest;
import com.greedy.leaderboard.dto.UserProfileResponse;
import com.greedy.leaderboard.entity.User;
import com.greedy.leaderboard.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserProfileResponse createUser(UserProfileRequest userProfileRequest) {
        // 1. 사용자 닉네임 중복 체크
        Optional<User> userByNickname = userRepository.findByNickname(userProfileRequest.getNickname());
        if (userByNickname.isPresent()) {
            throw new IllegalArgumentException("이미 사용 중인 닉네임입니다.");
        }

        // 2. 사용자 전화번호 중복 체크 (..?중복값이 들어올까....)
        if (userProfileRequest.getPhone() != null) {
            Optional<User> userByPhone = userRepository.findByPhone(userProfileRequest.getPhone());
            if (userByPhone.isPresent()) {
                throw new IllegalArgumentException("이미 등록된 전화번호입니다.");
            }
        }

        // 3. 사용자 Id 생성 (랜덤 4자리 문자열)
        String userId = generateRandomId();

        User newUser = new User(userId, userProfileRequest.getNickname(), userProfileRequest.getPhone());
        userRepository.save(newUser);

        return new UserProfileResponse(newUser);
    }

    private String generateRandomId() {
        String baseCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder userId = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            userId.append(baseCharacters.charAt(random.nextInt(baseCharacters.length())));
        }
        return userId.toString();
    }

}
