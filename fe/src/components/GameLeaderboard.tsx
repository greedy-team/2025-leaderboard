import React from "react";
import { GameCard } from "./GameCard";
import type { GameScore } from "../types/leaderboard";

interface Props {
  gameScores: Record<string, GameScore[]>;
  titles: string[];
}

const leftPos = [0, 340, 680, 1020];

export const GameLeaderboard: React.FC<Props> = ({ gameScores, titles }) => (
  <div className="w-[1320px] h-[498px] absolute left-1/2 -translate-x-1/2 top-[512px] overflow-visible">
    <p className="absolute left-[7px] top-[12px] text-[45px] font-bold text-black">
      🎮 미니게임 순위표
    </p>

    {titles.map((title, idx) => (
      <GameCard
        key={idx}
        title={title}
        scores={gameScores[title] ?? []}
        left={leftPos[idx]}
      />
    ))}
  </div>
);
