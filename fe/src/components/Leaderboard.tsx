import GameLeaderboard from "./GameLeaderboard";
import OverallLeaderboard from "./OverallLeaderboard";

const games: string[] = [
  "그린이 목 늘이기",
  "Keyzzle",
  "피카츄 배구",
  "수강신청 연습",
];

function Leaderboard() {
  /*
API Call
*/

  return (
    <div className="space-y-12">
      {games.map((game) => (
        <div key={game}>
          <h2 className="text-2xl font-semibold mb-4">{game} 순위</h2>
          <GameLeaderboard gameName={game} />
        </div>
      ))}
      <div>
        <h2 className="text-2xl font-semibold mb-4">종합 순위</h2>
        <OverallLeaderboard />
      </div>
    </div>
  );
}

export default Leaderboard;
