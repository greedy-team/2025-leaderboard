package com.greedy.leaderboard.repository;

import com.greedy.leaderboard.entity.game.Allcll;
import com.greedy.leaderboard.entity.game.GreenyNeck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AllcllRepository extends JpaRepository<Allcll, Long> {
    Optional<Allcll> findByUserId(String userId);

    @Query("SELECT a FROM Allcll a JOIN FETCH a.user")
    List<Allcll> findAllWithUser();

}
