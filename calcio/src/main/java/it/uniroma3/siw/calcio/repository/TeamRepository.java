package it.uniroma3.siw.calcio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import it.uniroma3.siw.calcio.model.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {

    boolean existsByNameAndYearFoundation(String name, int yearFoundation);

    boolean existsByNameAndYearFoundationAndIdNot(String name, int yearFoundation, Long id);

    // Utilizziamo @EntityGraph per evitare il problema delle N+1 query pre-caricando la collezione 'players'.
    // IMPORTANTE: Limitiamo il fetch ad una sola List ('players') per evitare l'eccezione
    // MultipleBagFetchException (che Hibernate lancia se si tenta il fetch multiplo di liste per rischio prodotto cartesiano).
    @EntityGraph(attributePaths = {"players"})
    Optional<Team> findByIdWithPlayers(Long id);
}
