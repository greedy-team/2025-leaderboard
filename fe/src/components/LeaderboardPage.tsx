import React, { useEffect, useState } from "react";
import axios from "axios";
import { Header } from "../components/Header";
import { OverallLeaderboard } from "../components/OverallLeaderboard";
import { GameLeaderboard } from "./GameLeaderboard";
import type { Player, GameScore } from "../types/leaderboard";
import { mockURL } from "../components/api/api";

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
        const response = await axios.get(`${mockURL}/players`);
        const players: Player[] = response.data;
        console.log(players);

        setOverall(players);

        const gamesData = Object.fromEntries(
          gameTitles.map((_, idx) => [
            idx,
            players.map((player) => ({
              name: player.name,
              score: Math.floor(Math.random() * 200) + 300,
            })),
          ])
        ) as Record<string, GameScore[]>;

        setGames(gamesData);
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
    <div className="w-[1440px] h-[1024px] relative overflow-hidden bg-white font-sans">
      <Header />
      <OverallLeaderboard players={overall} />
      <GameLeaderboard gameScores={games} titles={gameTitles} />
    </div>
  );
};
