package com.greedy.leaderboard.repository;

import com.greedy.leaderboard.entity.game.GreenyNeck;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface GreenyNeckRepository extends JpaRepository<GreenyNeck, Long> {
    Optional<GreenyNeck> findByUserId(String userId);

    @Query("SELECT g FROM GreenyNeck g JOIN FETCH g.user")
    List<GreenyNeck> findAllWithUser();

}
