import React from "react";
import type { Player } from "../types/leaderboard";

interface OverallCardProps {
  player: Player;
  left: number; // x-offset(px)
  barHeight: number;
  barTop: number;
  barColor: string;
  medal?: string;
  medalFs?: string;
}

export const OverallCard: React.FC<OverallCardProps> = ({
  player,
  left,
  barHeight,
  barTop,
  barColor,
  medal,
  medalFs = "",
}) => {
  const nameTop = barTop - 83;
  return (
    <div className="w-60 h-[340px] absolute" style={{ left, top: 80 }}>
      {/* bar */}
      <div
        className="w-48 absolute left-[23px]"
        style={{ height: barHeight, top: barTop, backgroundColor: barColor }}
      />

      {/* medal – */}
      {medal && (
        <p
          className={`absolute left-1/2 -translate-x-1/2 -translate-y-1/4 font-bold text-black ${medalFs}`}
          style={{ top: barTop + 25 }}
        >
          {medal}
        </p>
      )}

      {/* name & score */}
      <p
        className="absolute left-1/2 -translate-x-1/2 text-center font-bold text-black"
        style={{ top: nameTop }}
      >
        <span className="block text-[32px]">{player.name}</span>
        <span className="text-2xl">{player.score}점</span>
      </p>
    </div>
  );
};
