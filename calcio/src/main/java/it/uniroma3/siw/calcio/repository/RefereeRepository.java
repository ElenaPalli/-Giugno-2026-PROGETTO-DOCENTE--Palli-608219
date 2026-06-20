package it.uniroma3.siw.calcio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.uniroma3.siw.calcio.model.Referee;

public interface RefereeRepository extends JpaRepository<Referee, Long> {

    boolean existsByRefereeCode(String refereeCode);

    boolean existsByRefereeCodeAndIdNot(String refereeCode, Long id);

}
