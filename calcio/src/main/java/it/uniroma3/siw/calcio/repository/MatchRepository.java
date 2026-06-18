package it.uniroma3.siw.calcio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.uniroma3.siw.calcio.model.Match;
import it.uniroma3.siw.calcio.model.Team;

public interface MatchRepository extends JpaRepository<Match, Long> {
    List<Match> findByHomeTeam(Team homeTeam);

    List<Match> findByAwayTeam(Team awayTeam);
}
