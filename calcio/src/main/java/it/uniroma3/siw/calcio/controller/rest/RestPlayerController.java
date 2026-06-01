package it.uniroma3.siw.calcio.controller.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.uniroma3.siw.calcio.model.Player;
import it.uniroma3.siw.calcio.service.PlayerService;

@RestController
@RequestMapping("/rest/players")
public class RestPlayerController {
    private final PlayerService playerService;

    public RestPlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public List<Player> list() {
        return playerService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Player> show(@PathVariable Long id) {
        return playerService.findById(id);
    }
}
