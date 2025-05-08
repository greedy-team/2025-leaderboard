import React from "react";
import logo from "./donggrang-greedy-1.png";

export const Header: React.FC = () => (
  <div className="w-[1200px] h-[101px] absolute left-[120px] top-0 overflow-hidden">
    <div className="w-[284px] h-[79px] absolute left-[458px] top-[22px] select-none">
      <p className="absolute left-[89px] top-[11px] text-5xl font-bold text-black">
        GREEDY
      </p>
      <img
        src={logo}
        alt="GREEDY"
        className="w-[79px] h-[79px] absolute -left-px -top-px object-cover"
      />
    </div>
  </div>
);
