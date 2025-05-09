package com.greedy.leaderboard.repository;

import com.greedy.leaderboard.entity.game.CourseRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRegistrationRepository extends JpaRepository<CourseRegistration, Long> {
    Optional<CourseRegistration> findByUserId(String userId);


}
