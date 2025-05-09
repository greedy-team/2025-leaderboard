package com.greedy.leaderboard.repository;

import com.greedy.leaderboard.entity.game.PikachuVolley;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PikachuVolleyRepository extends JpaRepository<PikachuVolley, Long> {
    Optional<PikachuVolley> findByUserId(String userId);

    @Query("select p from PikachuVolley p join fetch p.user")
    List<PikachuVolley> findAllWithUser();
}
