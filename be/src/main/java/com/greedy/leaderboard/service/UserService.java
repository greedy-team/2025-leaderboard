package com.greedy.leaderboard.service;

import com.greedy.leaderboard.dto.UserProfileRequest;
import com.greedy.leaderboard.dto.UserProfileResponse;
import com.greedy.leaderboard.entity.User;
import com.greedy.leaderboard.exception.DuplicateUserInfoException;
import com.greedy.leaderboard.exception.NotFoundUserException;
import com.greedy.leaderboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final IdGenerator idGenerator;

    @Transactional
    public UserProfileResponse createUser(UserProfileRequest userProfileRequest) {

        // 1. 사용자 닉네임 중복 체크
        if (userRepository.existsByNickname(userProfileRequest.getNickname())) {
            throw new DuplicateUserInfoException("닉네임 중복", "이미 사용 중인 닉네임입니다.");
        }

        // 1. 사용자 전화번호 중복 체크
        if (userProfileRequest.getNickname() != null) {
            if (userRepository.existsByPhone(userProfileRequest.getPhone())) {
                throw new DuplicateUserInfoException("전화번호 중복", "이미 등록된 전화번호입니다.");
            }
        }

        // 2. 사용자 Id 생성 (랜덤 4자리 문자열)
        String userId = generateRandomId();

        // 3. 사용자 등록 (DB 저장)
        User newUser = new User(userId, userProfileRequest.getNickname(), userProfileRequest.getPhone());
        userRepository.save(newUser);

        return new UserProfileResponse(newUser);
    }

    private String generateRandomId() {
        String userId;
        int maxRetries = 2000;  // 최대 시도 횟수
        int retries = 0;
        do {
            userId = idGenerator.generateId();
            retries++;
            if (retries >= maxRetries) {
                throw new RuntimeException("ID 생성 실패: 너무 많은 시도 후 중복 발생.");
            }
        } while (userRepository.existsByUserIdCaseSensitive(userId) == 1L);
        return userId;
    }

    public UserProfileResponse getUserById(String userId) {
        User findUser = userRepository.findByIdCaseSensitive(userId).orElseThrow(() ->
                new NotFoundUserException("등록되지 않은 유저", "아이디가 " + userId + "인 유저는 존재하지 않습니다."));
        return new UserProfileResponse(findUser);
    }

    public UserProfileResponse getUserByPhone(String phone) {
        User findUser = userRepository.findByPhone(phone).orElseThrow(() ->
                new NotFoundUserException("등록되지 않은 유저", "전화번호가 " + phone + "인 유저는 존재하지 않습니다."));
        return new UserProfileResponse(findUser);
    }
}
