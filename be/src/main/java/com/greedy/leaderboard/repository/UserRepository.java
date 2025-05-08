package com.greedy.leaderboard.repository;

import com.greedy.leaderboard.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
