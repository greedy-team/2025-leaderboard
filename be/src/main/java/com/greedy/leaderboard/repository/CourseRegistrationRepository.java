package com.greedy.leaderboard.repository;

import com.greedy.leaderboard.entity.game.CourseRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CourseRegistrationRepository extends JpaRepository<CourseRegistration, Long> {
    Optional<CourseRegistration> findByUserId(String userId);

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
    List<CourseRegistration> findTop5WithTies();
}
