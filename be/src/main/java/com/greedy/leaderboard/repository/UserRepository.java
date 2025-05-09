package com.greedy.leaderboard.repository;

import com.greedy.leaderboard.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    // 닉네임 중복 체크
    Optional<User> findByNickname(String nickname);

    // 전화번호 중복 체크
    Optional<User> findByPhone(String phone);
}
