package com.greedy.leaderboard.repository;

import com.greedy.leaderboard.entity.game.Allcll;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AllcllRepository extends JpaRepository<Allcll, Long> {
    Optional<Allcll> findByUserId(String userId);


}
