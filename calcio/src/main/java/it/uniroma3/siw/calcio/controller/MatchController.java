package it.uniroma3.siw.calcio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.calcio.model.Comment;
import it.uniroma3.siw.calcio.model.Match;
import it.uniroma3.siw.calcio.model.MatchState;
import it.uniroma3.siw.calcio.service.CommentService;
import it.uniroma3.siw.calcio.service.MatchService;
import it.uniroma3.siw.calcio.service.RefereeService;
import it.uniroma3.siw.calcio.service.TeamService;
import it.uniroma3.siw.calcio.service.TournamentService;
import jakarta.validation.Valid;

@Controller
public class MatchController {
    private final MatchService matchService;
    private final TournamentService tournamentService;
    private final TeamService teamService;
    private final RefereeService refereeService;
    private final CommentService commentService;

    public MatchController(MatchService matchService, TournamentService tournamentService, TeamService teamService,
            RefereeService refereeService, CommentService commentService) {
        this.matchService = matchService;
        this.tournamentService = tournamentService;
        this.teamService = teamService;
        this.refereeService = refereeService;
        this.commentService = commentService;
    }

    @GetMapping("/matches")
    public String list(@RequestParam(required = false) Long teamId, Model model) {
        if (teamId != null) {
            model.addAttribute("matches", matchService.findByTeamId(teamId));
        } else {
            model.addAttribute("matches", matchService.findAllWithDetails());
        }
        // passa la lista di tutte le squadre per popolare tendina->selectedTeamId
        // per dice al file HTML quale squadra era stata appena selezionata, così
        // da farle apparire l'attributo selected e poi filtrare
        model.addAttribute("teams", teamService.findAll());
        model.addAttribute("selectedTeamId", teamId);
        return "matches/listMatch";
    }

    @GetMapping("/matches/{id}")
    public String show(@PathVariable Long id, Model model) {
        Match match = matchService.findByIdWithDetails(id).orElse(null); // optional ??
        model.addAttribute("match", match);
        if (match != null) {
            model.addAttribute("comments", commentService.findByMatch(match));
            model.addAttribute("newComment", new Comment());
        }
        return "matches/showMatch";
    }

    @GetMapping("/admin/matches/new")
    public String createForm(Model model) {
        model.addAttribute("match", new Match());
        model.addAttribute("tournaments", tournamentService.findAll());
        model.addAttribute("teams", teamService.findAll());
        model.addAttribute("referees", refereeService.findAll());
        model.addAttribute("states", MatchState.values());
        return "admin/matches/form";
    }

    @PostMapping("/admin/matches")
    public String save(@Valid @ModelAttribute("match") Match match, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("tournaments", tournamentService.findAll());
            model.addAttribute("teams", teamService.findAll());
            model.addAttribute("referees", refereeService.findAll());
            model.addAttribute("states", MatchState.values());
            return "admin/matches/form";
        }
        matchService.save(match);
        return "redirect:/matches";
    }

    @GetMapping("/admin/matches/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("match", matchService.findById(id).orElse(null));
        model.addAttribute("tournaments", tournamentService.findAll());
        model.addAttribute("teams", teamService.findAll());
        model.addAttribute("referees", refereeService.findAll());
        model.addAttribute("state", MatchState.values());
        return "admin/matches/form";
    }

    @PostMapping("/admin/matches/{id}/delete")
    public String delete(@PathVariable Long id) {
        matchService.deleteById(id);
        return "redirect:/matches";
    }
}