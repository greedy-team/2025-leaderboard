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

    // keyzzle 게임은 시간 기준이라 낮은게 높은 순위를 가져야 함
    @Query(value = "WITH ranked as ( " +
            "SELECT u.nickname, k.score, RANK() OVER (ORDER BY k.score ASC) AS `rank` " +
            "FROM keyzzle k " +
            "JOIN users u on k.user_id = u.user_id " +
            " )" +
            "SELECT * FROM ranked WHERE `rank` <= 5 ORDER BY `rank` ASC ", nativeQuery = true)
    List<RankQueryInterface> findTop5WithRank();

    @Query(value = "SELECT u.nickname, 3 AS point " +
            "FROM keyzzle k " +
            "JOIN users u ON k.user_id = u.user_id", nativeQuery = true)
    List<OverallPointInterface> findAllOverallPoint();
}
