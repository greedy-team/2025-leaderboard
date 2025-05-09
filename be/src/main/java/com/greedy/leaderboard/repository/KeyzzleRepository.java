package com.greedy.leaderboard.repository;

import com.greedy.leaderboard.entity.game.Keyzzle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface KeyzzleRepository extends JpaRepository<Keyzzle, Long> {
    Optional<Keyzzle> findByUserId(String userId);

    @Query("select k from Keyzzle k join fetch k.user")
    List<Keyzzle> findAllWithUser();
}
