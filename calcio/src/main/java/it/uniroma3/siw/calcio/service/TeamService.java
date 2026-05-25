package it.uniroma3.siw.calcio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.calcio.exception.DuplicateMovieException;
import it.uniroma3.siw.calcio.model.Team;
import it.uniroma3.siw.calcio.repository.TeamRepository;

@Service
public class TeamService {

   private final TeamRepository teamRepository;

    public TeamService(TeamRepository movieRepository) {
        this.teamRepository = movieRepository;
    }

    public List<Team> findAll() {
        return teamRepository.findAll();
    }

    public Optional<Team> findById(Long id) {
        return teamRepository.findById(id);
    }

    @Transactional
    public Team save(Team team) throws DuplicateTeamException {
        boolean duplicate = team.getId() == null
            ? teamRepository.existsByNameAndYear(team.getName(), team.getYearFoundation())
            : teamRepository.existsByNameAndYearAndIdNot(team.getName(), team.getYearFoundation(), team.getId());
        if (duplicate) {
            throw new DuplicateTeamException(team.getName(), team.getYearFoundation());
        }
        return teamRepository.save(team);
    }

    @Transactional
    public void deleteById(Long id) {
        teamRepository.deleteById(id);
    }
}
