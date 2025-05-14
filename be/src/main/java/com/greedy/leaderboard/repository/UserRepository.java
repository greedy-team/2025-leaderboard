package com.greedy.leaderboard.repository;

import com.greedy.leaderboard.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByNickname(String nickname);

    @Query(value = "SELECT * FROM users WHERE BINARY user_id = :id", nativeQuery = true)
    Optional<User> findByIdCaseSensitive(@Param("id") String id);

    @Query(value = "SELECT EXISTS(SELECT 1 FROM users WHERE BINARY user_id = :userId)", nativeQuery = true)
    Long existsByUserIdCaseSensitive(@Param("userId") String userId);

    Boolean existsByPhone(String Phone);
    Boolean existsByNickname(String nickname);
}
