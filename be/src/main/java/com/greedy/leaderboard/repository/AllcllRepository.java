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

    // Native Query 로 랭킹 집계
    @Query(value = "WITH ranked AS ( " +
            "SELECT u.nickname, a.score, RANK() OVER (ORDER BY a.score DESC) AS `rank` " +
            "FROM allcll a " +
            "JOIN users u on a.user_id = u.user_id " +
            " ) " +
            "SELECT * FROM ranked WHERE `rank` <= 5 ORDER BY `rank` ASC ", nativeQuery = true)
    List<RankQueryInterface> findTop5WithRank();
}
