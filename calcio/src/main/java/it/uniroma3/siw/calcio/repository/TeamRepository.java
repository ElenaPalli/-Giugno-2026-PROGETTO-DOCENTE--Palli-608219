package it.uniroma3.siw.calcio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.uniroma3.siw.calcio.model.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {
    
    //List<Team> findByTitleContainingIgnoreCase(String name);

    boolean existsByNameAndYear(String name, int yearFoundation);

    boolean existsByNameAndYearAndIdNot(String name, int yearFoundation, Long id);
}
