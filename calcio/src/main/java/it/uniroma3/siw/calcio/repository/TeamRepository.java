package it.uniroma3.siw.calcio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.uniroma3.siw.calcio.model.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {

    boolean existsByNameAndYearFoundation(String name, int yearFoundation);

    boolean existsByNameAndYearFoundationAndIdNot(String name, int yearFoundation, Long id);
}
