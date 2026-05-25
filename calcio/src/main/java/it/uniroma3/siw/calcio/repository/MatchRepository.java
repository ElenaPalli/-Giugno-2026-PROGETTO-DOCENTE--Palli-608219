package it.uniroma3.siw.calcio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.uniroma3.siw.calcio.model.Match;

public interface MatchRepository extends JpaRepository<Match, Long>{
    
}
