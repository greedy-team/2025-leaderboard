import React from "react";
import type { Player } from "../types/leaderboard";
import { OverallCard } from "../components/OverallCard";

const leftPos = [0, 280, 560, 840, 1120];
const heights = [229, 178, 123, 79, 46];
const tops = [74, 125, 180, 224, 257];
const colors = ["#ffc756", "#ff7474", "#60bdff", "#c669ff", "#70ed59"];
const medals = ["🥇", "🥈", "🥉", "", ""];
const medalFs = ["text-[160px]", "text-[140px]", "text-[100px]", "", ""];

interface Props {
  players: Player[];
}
export const OverallLeaderboard: React.FC<Props> = ({ players }) => (
  <div className="w-[1320px] h-[375px] absolute left-1/2 -translate-x-1/2 top-[120px] overflow-visible">
    <p className="absolute left-[7px] top-0 text-[45px] font-bold text-black">
      🏆 종합 순위표
    </p>

    {players.slice(0, 5).map((p, i) => (
      <OverallCard
        key={i}
        player={p}
        left={leftPos[i]}
        barHeight={heights[i]}
        barTop={tops[i]}
        barColor={colors[i]}
        medal={medals[i]}
        medalFs={medalFs[i]}
      />
    ))}
  </div>
);
