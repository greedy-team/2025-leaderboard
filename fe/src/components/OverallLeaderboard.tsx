const allScores: Record<string, number[]> = {
  Alice: [95, 80, 83, 80],
  Bob: [90, 75, 85],
  Carol: [85, 88, 85],
  Dave: [70, 90],
};

function OverallLeaderboard() {
  const combinedScores = Object.entries(allScores).map(([name, scores]) => ({
    name,
    total: scores.reduce((a, b) => a + b, 0),
  }));

  const sorted = combinedScores.sort((a, b) => b.total - a.total);

  return (
    <table className="table-auto w-full border border-gray-300">
      <thead>
        <tr className="bg-gray-100">
          <th className="px-4 py-2">순위</th>
          <th className="px-4 py-2">이름</th>
          <th className="px-4 py-2">총점</th>
        </tr>
      </thead>
      <tbody>
        {sorted.map((entry, idx) => (
          <tr key={entry.name}>
            <td className="px-4 py-2 text-center">{idx + 1}</td>
            <td className="px-4 py-2">{entry.name}</td>
            <td className="px-4 py-2 text-center">{entry.total}</td>
          </tr>
        ))}
      </tbody>
    </table>
  );
}

export default OverallLeaderboard;
