package it.uniroma3.siw.calcio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.uniroma3.siw.calcio.model.Player;
import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long>{
    
    boolean existsByNameAndSurname(String name, String surname);

    boolean existsByNameAndSurnameAndIdNot(String name, String surname, Long id);

    @Query("SELECT p FROM Player p LEFT JOIN FETCH p.team")
    List<Player> findAllWithTeam();
}
