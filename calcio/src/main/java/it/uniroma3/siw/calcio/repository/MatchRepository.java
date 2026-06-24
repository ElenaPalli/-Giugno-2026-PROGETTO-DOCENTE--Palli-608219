package it.uniroma3.siw.calcio.repository;

import java.util.List;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw.calcio.model.Match;
import it.uniroma3.siw.calcio.model.Team;

public interface MatchRepository extends JpaRepository<Match, Long> {
    List<Match> findByHomeTeam(Team homeTeam);

    List<Match> findByAwayTeam(Team awayTeam);

    List<Match> findByHomeTeamIdOrAwayTeamId(Long homeTeamId, Long awayTeamId);

    // Utilizziamo una @Query con JOIN FETCH espliciti invece di @EntityGraph.
    // In questo caso, essendo 'homeTeam', 'awayTeam' e 'referee' relazioni verso
    // singole entità (ManyToOne),
    // possiamo pre-caricarle tutte insieme in una singola query più grande
    @Query("SELECT m FROM Match m JOIN FETCH m.homeTeam JOIN FETCH m.awayTeam LEFT JOIN FETCH m.referee WHERE m.id = :id")
    Optional<Match> findByIdWithDetails(@Param("id") Long id);

    @Query("SELECT m FROM Match m JOIN FETCH m.homeTeam JOIN FETCH m.awayTeam LEFT JOIN FETCH m.referee")
    List<Match> findAllWithDetails();
}
