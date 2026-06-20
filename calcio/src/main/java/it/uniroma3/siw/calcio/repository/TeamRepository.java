package it.uniroma3.siw.calcio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw.calcio.model.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {

    boolean existsByNameAndYearFoundation(String name, int yearFoundation);

    boolean existsByNameAndYearFoundationAndIdNot(String name, int yearFoundation, Long id);

    Optional<Team> findByNameIgnoreCase(String name);

    // Utilizziamo @EntityGraph per evitare il problema delle N+1 query
    // pre-caricando la collezione 'players'.
    @EntityGraph(attributePaths = { "players" })
    @Query("SELECT t FROM Team t WHERE t.id = :id")
    Optional<Team> findByIdWithPlayers(@Param("id") Long id);
}
