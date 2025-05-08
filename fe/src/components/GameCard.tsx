import React from "react";
import type { GameScore } from "../types/leaderboard";

interface Props {
  title: string;
  scores: GameScore[];
  left: number; // x offset (px)
}

export const GameCard: React.FC<Props> = ({ title, scores, left }) => (
  <div className="w-[260px] h-[360px] absolute top-[100px]" style={{ left }}>
    <div className="w-full h-full bg-white rounded-[25px] shadow-[0_4px_20px_rgba(0,0,0,0.25)] flex flex-col items-center justify-center px-4 py-6">
      <h3 className="text-3xl font-bold mb-6 whitespace-nowrap text-center">
        {title}
      </h3>

      <ol className="space-y-3">
        {scores.slice(0, 5).map((s, idx) => (
          <li key={idx} className="text-lg font-bold whitespace-nowrap">
            {idx + 1}등 - {s.name} ( {s.score}점 )
          </li>
        ))}
      </ol>
    </div>
  </div>
);
