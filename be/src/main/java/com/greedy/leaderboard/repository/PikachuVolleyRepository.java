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

    @Query(value = "WITH ranked AS ( " +
            "SELECT u.nickname, p.score, RANK() OVER (ORDER BY p.score DESC) AS `rank` " +
            "FROM pikachu_volley p " +
            "JOIN users u on p.user_id = u.user_id " +
            " ) " +
            "SELECT * FROM ranked WHERE `rank` <= 5 ORDER BY `rank` ASC ", nativeQuery = true)
    List<RankQueryInterface> findTop5WithRank();

    @Query(value = "SELECT u.nickname, 3 AS point " +
            "FROM pikachu_volley p " +
            "JOIN users u ON p.user_id = u.user_id", nativeQuery = true)
    List<OverallPointInterface> findAllOverallPoint();
}
