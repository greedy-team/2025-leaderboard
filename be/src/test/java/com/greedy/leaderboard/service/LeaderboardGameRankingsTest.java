package com.greedy.leaderboard.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import com.greedy.leaderboard.dto.GameRankingResponse;
import com.greedy.leaderboard.repository.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
class LeaderboardGameRankingsTest {

    @Mock
    private AllcllRepository allcllRepository;

    @Mock
    private GreenyNeckRepository greenyNeckRepository;

    @Mock
    private KeyzzleRepository keyzzleRepository;

    @Mock
    private PikachuVolleyRepository pikachuVolleyRepository;

    @InjectMocks
    private LeaderboardService leaderboardService;

    private List<RankQueryInterface> mockRankQueryInterfaces;

    @BeforeEach
    void setup() {
        RankQueryInterface mock1 = mock(RankQueryInterface.class);
        RankQueryInterface mock2 = mock(RankQueryInterface.class);

        when(mock1.getRank()).thenReturn(1);
        when(mock1.getNickname()).thenReturn("player1");
        when(mock1.getScore()).thenReturn(100.0);

        when(mock2.getRank()).thenReturn(2);
        when(mock2.getNickname()).thenReturn("player2");
        when(mock2.getScore()).thenReturn(95.0);

        mockRankQueryInterfaces = Arrays.asList(mock1, mock2);
    }

    @Test
    @DisplayName("ALLCLL 게임 랭킹 조회 테스트")
    void testGetSingleGameRankingsAllcll() {
        // Mock 리턴 설정
        when(allcllRepository.findTop5WithRank()).thenReturn(mockRankQueryInterfaces);

        // 실제 서비스 메서드 호출
        GameRankingResponse response = leaderboardService.getSingleGameRankings("allcll");

        // Assertion
        assertThat(response.getGameName()).isEqualTo("올클");
        assertThat(response.getRankings()).hasSize(2);
        assertThat(response.getRankings().get(0).getNickname()).isEqualTo("player1");
        assertThat(response.getRankings().get(0).getScore()).isEqualTo(100.0);
    }

    @Test
    @DisplayName("GREENY_NECK 게임 랭킹 조회 테스트")
    void testGetSingleGameRankingsGreenyNeck() {
        when(greenyNeckRepository.findTop5WithRank()).thenReturn(mockRankQueryInterfaces);

        GameRankingResponse response = leaderboardService.getSingleGameRankings("greeny-neck");

        assertThat(response.getGameName()).isEqualTo("그린이 목 늘이기");
        assertThat(response.getRankings()).hasSize(2);
        assertThat(response.getRankings().get(0).getNickname()).isEqualTo("player1");
        assertThat(response.getRankings().get(0).getScore()).isEqualTo(100.0);
    }

    @Test
    @DisplayName("KEYZZLE 게임 랭킹 조회 테스트")
    void testGetSingleGameRankingsKeyzzle() {
        when(keyzzleRepository.findTop5WithRank()).thenReturn(mockRankQueryInterfaces);

        GameRankingResponse response = leaderboardService.getSingleGameRankings("keyzzle");

        assertThat(response.getGameName()).isEqualTo("Keyzzle");
        assertThat(response.getRankings()).hasSize(2);
        assertThat(response.getRankings().get(0).getNickname()).isEqualTo("player1");
        assertThat(response.getRankings().get(0).getScore()).isEqualTo(100.0);
    }

    @Test
    @DisplayName("PIKACHU_VOLLEY 게임 랭킹 조회 테스트")
    void testGetSingleGameRankingsPikachuVolley() {
        when(pikachuVolleyRepository.findTop5WithRank()).thenReturn(mockRankQueryInterfaces);

        GameRankingResponse response = leaderboardService.getSingleGameRankings("pikachu-volley");

        assertThat(response.getGameName()).isEqualTo("피카츄 배구");
        assertThat(response.getRankings()).hasSize(2);
        assertThat(response.getRankings().get(0).getNickname()).isEqualTo("player1");
        assertThat(response.getRankings().get(0).getScore()).isEqualTo(100.0);
    }
}
