package com.greedy.leaderboard.repository;

import com.greedy.leaderboard.entity.game.GreenyNeck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface GreenyNeckRepository extends JpaRepository<GreenyNeck, Long> {
    Optional<GreenyNeck> findByUserId(String userId);

    @Query("""
        SELECT c FROM CourseRegistration c 
        WHERE c.score >= (
            SELECT cr.score 
            FROM CourseRegistration cr 
            ORDER BY cr.score DESC 
            LIMIT 1 OFFSET 4
        )
        ORDER BY c.score DESC, c.id ASC
    """)
    List<GreenyNeck> findTop5WithTies();
}
