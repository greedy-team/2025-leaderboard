package com.greedy.leaderboard.service;

import com.greedy.leaderboard.dto.LeaderBoardResponse;
import com.greedy.leaderboard.entity.game.Allcll;
import com.greedy.leaderboard.entity.game.GreenyNeck;
import com.greedy.leaderboard.entity.game.Keyzzle;
import com.greedy.leaderboard.entity.game.PikachuVolley;
import com.greedy.leaderboard.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LeaderboardService {

    private final UserRepository userRepository;
    private final PikachuVolleyRepository pikachuVolleyRepository;
    private final KeyzzleRepository keyzzleRepository;
    private final GreenyNeckRepository greenyNeckRepository;
    private final AllcllRepository allcllRepository;






    

}
