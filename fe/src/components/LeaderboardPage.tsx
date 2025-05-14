import React, { useEffect, useState } from "react";
import axios from "axios";
import { Header } from "../components/Header";
import { OverallLeaderboard } from "../components/OverallLeaderboard";
import { GameLeaderboard } from "./GameLeaderboard";
import type { Player, GameScore } from "../types/leaderboard";
import { apiURL } from "../components/api/api";

const gameTitles = ["그린이 목 늘이기", "Keyzzle", "피카츄 배구", "ALLCLL"];

const LoadingSpinner = () => (
  <div className="flex flex-col items-center justify-center h-screen">
    <div className="animate-spin rounded-full h-16 w-16 border-t-2 border-b-2 border-[#007354]"></div>
    <p className="mt-4 text-gray-600">로딩 중...</p>
  </div>
);

export const LeaderboardPage: React.FC = () => {
  const [overall, setOverall] = useState<Player[]>([]);
  const [games, setGames] = useState<Record<string, GameScore[]>>({});
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        setLoading(true);

        // 전체 순위 API 호출
        const overallRes = await axios.get(`${apiURL}/leader-board/overall`);
        const overallPlayers: Player[] = overallRes.data.rankings.map(
          (p: any) => ({
            name: p.nickname,
            score: p.score,
          })
        );
        setOverall(overallPlayers);

        // 미니게임별 순위 API 호출
        const gameApiMap = {
          "그린이 목 늘이기": "greeny-neck",
          Keyzzle: "keyzzle",
          "피카츄 배구": "pikachu-volley",
          ALLCLL: "allcll",
        } as const;

        const gameResults = await Promise.all(
          Object.entries(gameApiMap).map(async ([korTitle, engName]) => {
            const res = await axios.get(`${apiURL}/leader-board/${engName}`);
            const scores: GameScore[] = res.data.rankings.map((entry: any) => ({
              name: entry.nickname,
              score: entry.score,
            }));
            return [korTitle, scores] as [string, GameScore[]];
          })
        );

        const gameScoreMap = Object.fromEntries(gameResults);
        setGames(gameScoreMap);

        setError(null);
      } catch (error) {
        setError("데이터를 불러오는데 실패했습니다.");
        console.error("Error fetching data:", error);
      } finally {
        setLoading(false);
      }
    };

    fetchData();
  }, []);

  if (loading) {
    return <LoadingSpinner />;
  }

  if (error) {
    return (
      <div className="flex justify-center items-center h-screen text-red-500">
        {error}
      </div>
    );
  }

  return (
    <div className="min-w-[1440px] min-h-[1024px] relative overflow-visible bg-white font-sans">
      <Header />
      <OverallLeaderboard players={overall} />
      <GameLeaderboard gameScores={games} titles={gameTitles} />
    </div>
  );
};
