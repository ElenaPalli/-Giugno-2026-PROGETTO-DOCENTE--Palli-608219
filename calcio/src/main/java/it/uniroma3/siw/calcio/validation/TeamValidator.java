package it.uniroma3.siw.calcio.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.calcio.model.Team;
import it.uniroma3.siw.calcio.repository.TeamRepository;

@Component
public class TeamValidator implements Validator {

    private final TeamRepository teamRepository;

    public TeamValidator(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Team.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Team team = (Team) target;
        
        boolean exists;
        if (team.getId() != null) {
            exists = teamRepository.existsByNameAndYearFoundationAndIdNot(team.getName(), team.getYearFoundation(), team.getId());
        } else {
            exists = teamRepository.existsByNameAndYearFoundation(team.getName(), team.getYearFoundation());
        }
        
        if (exists) {
            errors.reject("team.duplicate");
        }
    }
}
