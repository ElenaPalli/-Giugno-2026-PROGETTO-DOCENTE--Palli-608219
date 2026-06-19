package it.uniroma3.siw.calcio.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw.calcio.model.RankingRow;
import it.uniroma3.siw.calcio.model.Tournament;

public interface TournamentRepository extends JpaRepository<Tournament, Long> {
    @Query(value = "SELECT t.id AS teamId, t.name AS teamName, " +
            "SUM(CASE " +
            "  WHEN m.state = 'PLAYED' AND m.home_team_id = t.id THEN m.goals_home " +
            "  WHEN m.state = 'PLAYED' AND m.away_team_id = t.id THEN m.goals_away " +
            "  ELSE 0 END) AS goalsFor " +
            "FROM team t " +
            "JOIN tournament_teams tt ON t.id = tt.teams_id " +
            "LEFT JOIN match m ON m.tournament_id = tt.tournaments_id " +
            "                  AND (m.home_team_id = t.id OR m.away_team_id = t.id) " +
            "WHERE tt.tournaments_id = :tournamentId " +
            "GROUP BY t.id, t.name " +
            "ORDER BY goalsFor DESC", nativeQuery = true)
    List<RankingRow> calculateTournamentRanking(@Param("tournamentId") Long tournamentId);

    // EntityGraph è l'approccio ideale e dichiarativo per evitare N+1 Select
    // quando dobbiamo pre-caricare una singola collezione (in questo caso 'matches').
    @EntityGraph(attributePaths = {"matches"})
    Optional<Tournament> findByIdWithMatches(Long id);
}
