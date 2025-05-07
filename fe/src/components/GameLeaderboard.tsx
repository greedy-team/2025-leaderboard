const mockData: Record<string, { name: string; score: number }[]> = {
  "그린이 목 늘이기": [
    { name: "Alice", score: 95 },
    { name: "Bob", score: 90 },
    { name: "Carol", score: 85 },
  ],
  Keyzzle: [
    { name: "Alice", score: 80 },
    { name: "Bob", score: 75 },
    { name: "Dave", score: 70 },
  ],
  "피카츄 배구": [
    { name: "Carol", score: 88 },
    { name: "Bob", score: 85 },
    { name: "Alice", score: 83 },
  ],
  "수강신청 연습": [
    { name: "Dave", score: 90 },
    { name: "Carol", score: 85 },
    { name: "Alice", score: 80 },
  ],
};

function GameLeaderboard({ gameName }: { gameName: string }) {
  const data = mockData[gameName] || [];

  return (
    <table className="table-auto w-full border border-gray-300">
      <thead>
        <tr className="bg-gray-100">
          <th className="px-4 py-2">순위</th>
          <th className="px-4 py-2">이름</th>
          <th className="px-4 py-2">점수</th>
        </tr>
      </thead>
      <tbody>
        {data.map((entry, idx) => (
          <tr key={entry.name}>
            <td className="px-4 py-2 text-center">{idx + 1}</td>
            <td className="px-4 py-2">{entry.name}</td>
            <td className="px-4 py-2 text-center">{entry.score}</td>
          </tr>
        ))}
      </tbody>
    </table>
  );
}

export default GameLeaderboard;
