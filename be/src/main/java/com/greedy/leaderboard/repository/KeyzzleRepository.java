package com.greedy.leaderboard.repository;

import com.greedy.leaderboard.entity.game.Keyzzle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface KeyzzleRepository extends JpaRepository<Keyzzle, Long> {
    Optional<Keyzzle> findByUserId(String userId);

    @Query("""
        SELECT k FROM Keyzzle k 
        WHERE k.score >= (
            SELECT kz.score 
            FROM Keyzzle kz 
            ORDER BY kz.score DESC 
            LIMIT 1 OFFSET 4
        )
        ORDER BY k.score DESC, k.id ASC
    """)
    List<Keyzzle> findTop5WithTies();
}
