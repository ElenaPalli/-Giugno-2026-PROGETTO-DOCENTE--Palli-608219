package it.uniroma3.siw.calcio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.calcio.exception.DuplicateTeamException;
import it.uniroma3.siw.calcio.model.Team;
import it.uniroma3.siw.calcio.repository.TeamRepository;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<Team> findAll() {
        return this.teamRepository.findAll();
    }

    @Transactional
    public Optional<Team> findById(Long id) {
        Optional<Team> teamOpt = this.teamRepository.findById(id);
        if (teamOpt.isPresent()) {
            Team team = teamOpt.get();
            if (team.getPlayers() != null)
                team.getPlayers().size();
            if (team.getHomeMatches() != null)
                team.getHomeMatches().size();
            if (team.getAwayMatches() != null)
                team.getAwayMatches().size();
            if (team.getTournaments() != null) // AGGIUNTO DA ME
                team.getTournaments().size();
        }
        return teamOpt;
    }

    @Transactional
    public Team save(Team team) throws DuplicateTeamException {
        boolean duplicate = team.getId() == null
                ? this.teamRepository.existsByNameAndYearFoundation(team.getName(), team.getYearFoundation())
                : this.teamRepository.existsByNameAndYearFoundationAndIdNot(team.getName(), team.getYearFoundation(),
                        team.getId());
        if (duplicate) {
            throw new DuplicateTeamException(team.getName(), team.getYearFoundation());
        }
        return this.teamRepository.save(team);
    }

    @Transactional
    public void deleteById(Long id) {
        this.teamRepository.deleteById(id);
    }
}