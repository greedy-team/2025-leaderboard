import React, { useEffect, useState } from "react";
import { Header } from "../components/Header";
import { OverallLeaderboard } from "../components/OverallLeaderboard";
import { GameLeaderboard } from "./GameLeaderboard";
import type { Player, GameScore } from "../types/leaderboard";

const gameTitles = ["그린이 목 늘이기", "Keyzzle", "피카츄 배구", "ALLCLL"];

export const LeaderboardPage: React.FC = () => {
  const [overall, setOverall] = useState<Player[]>([]);
  const [games, setGames] = useState<Record<string, GameScore[]>>({});

  useEffect(() => {
    const mock: Player[] = Array.from({ length: 5 }, () => ({
      name: "김보예",
      score: 300,
    }));
    const gamesMock = Object.fromEntries(
      gameTitles.map((_, idx) => [idx, mock])
    ) as Record<string, GameScore[]>;
    setOverall(mock);
    setGames(gamesMock);
  }, []);

  return (
    <div className="w-[1440px] h-[1024px] relative overflow-hidden bg-white font-sans">
      <Header />
      <OverallLeaderboard players={overall} />
      <GameLeaderboard gameScores={games} titles={gameTitles} />
    </div>
  );
};
