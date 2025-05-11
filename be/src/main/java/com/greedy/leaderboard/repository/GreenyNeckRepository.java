package com.greedy.leaderboard.repository;

import com.greedy.leaderboard.entity.game.GreenyNeck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface GreenyNeckRepository extends JpaRepository<GreenyNeck, Long> {
    Optional<GreenyNeck> findByUser_UserId(String userId);

    @Query("SELECT g FROM GreenyNeck g JOIN FETCH g.user")
    List<GreenyNeck> findAllWithUser();

    // 그린이 게임은 시간 기준이라 낮은게 높은 순위를 가져야 함
    @Query(value = "WITH ranked as ( " +
            "SELECT u.nickname, g.score, RANK() OVER (ORDER BY g.score ASC) AS `rank` " +
            "FROM greeny_neck g " +
            "JOIN users u on g.user_id = u.user_id " +
            " )" +
            "SELECT * FROM ranked WHERE `rank` <= 5 ORDER BY `rank` ASC ", nativeQuery = true)
    List<RankQueryInterface> findTop5WithRank();

}
