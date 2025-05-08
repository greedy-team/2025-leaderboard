package com.greedy.leaderboard.repository;

import com.greedy.leaderboard.entity.game.PikachuVolley;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PikachuVolleyRepository extends JpaRepository<PikachuVolley, Long> {
    Optional<PikachuVolley> findByUserId(String userId);

}
