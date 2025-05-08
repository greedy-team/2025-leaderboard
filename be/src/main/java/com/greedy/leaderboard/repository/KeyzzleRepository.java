package com.greedy.leaderboard.repository;

import com.greedy.leaderboard.entity.game.Keyzzle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KeyzzleRepository extends JpaRepository<Keyzzle, Long> {
    Optional<Keyzzle> findByUserId(String userId);
}
