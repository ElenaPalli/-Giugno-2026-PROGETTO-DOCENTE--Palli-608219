package it.uniroma3.siw.calcio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import it.uniroma3.siw.calcio.model.Team;
import it.uniroma3.siw.calcio.model.Tournament;
import it.uniroma3.siw.calcio.repository.TeamRepository;
import it.uniroma3.siw.calcio.repository.TournamentRepository;

import java.io.InputStream;
import java.util.Base64;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseSeeder.class);

    private final TeamRepository teamRepository;
    private final TournamentRepository tournamentRepository;
    private final ResourceLoader resourceLoader;

    public DatabaseSeeder(TeamRepository teamRepository, TournamentRepository tournamentRepository, ResourceLoader resourceLoader) {
        this.teamRepository = teamRepository;
        this.tournamentRepository = tournamentRepository;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("Iniziando il seeding dei loghi delle squadre...");

        Iterable<Team> teams = teamRepository.findAll();
        for (Team team : teams) {
            // Se la squadra non ha un logo, cerchiamo un file col suo nome
            if (team.getLogo() == null) {
                // Tenta vari formati comuni
                String[] estensioni = { ".png", ".jpg", ".jpeg" };
                boolean logoTrovato = false;

                for (String ext : estensioni) {
                    String fileName = "classpath:static/images/teams/" + team.getName().toLowerCase() + ext;
                    Resource resource = resourceLoader.getResource(fileName);

                    if (resource.exists()) {
                        try (InputStream is = resource.getInputStream()) {
                            byte[] bytes = is.readAllBytes();
                            String base64Image = Base64.getEncoder().encodeToString(bytes);

                            // Determina il MIME type (semplificato)
                            String mimeType = ext.equals(".png") ? "image/png" : "image/jpeg";
                            team.setLogo("data:" + mimeType + ";base64," + base64Image);

                            teamRepository.save(team);
                            logger.info("✅ Logo caricato per la squadra: " + team.getName() + " da "
                                    + resource.getFilename());
                            logoTrovato = true;
                            break;
                        } catch (Exception e) {
                            logger.error("Errore nel leggere il file del logo per " + team.getName(), e);
                        }
                    }
                }

                if (!logoTrovato) {
                    logger.warn("⚠️ Nessun logo trovato per la squadra: " + team.getName()
                            + " (cercato in static/images/teams/)");
                }
            }
        }

        logger.info("Iniziando il seeding dei loghi dei tornei...");

        Iterable<Tournament> tournaments = tournamentRepository.findAll();
        for (Tournament tournament : tournaments) {
            // Se il torneo non ha un logo, cerchiamo un file col suo nome
            if (tournament.getLogo() == null || tournament.getLogo().isEmpty()) {
                // Tenta vari formati comuni
                String[] estensioni = { ".png", ".jpg", ".jpeg" };
                boolean logoTrovato = false;

                for (String ext : estensioni) {
                    String fileName = "classpath:static/images/tournaments/" + tournament.getName().toLowerCase() + ext;
                    Resource resource = resourceLoader.getResource(fileName);

                    if (resource.exists()) {
                        try (InputStream is = resource.getInputStream()) {
                            byte[] bytes = is.readAllBytes();
                            String base64Image = Base64.getEncoder().encodeToString(bytes);

                            // Determina il MIME type (semplificato)
                            String mimeType = ext.equals(".png") ? "image/png" : "image/jpeg";
                            tournament.setLogo("data:" + mimeType + ";base64," + base64Image);

                            tournamentRepository.save(tournament);
                            logger.info("✅ Logo caricato per il torneo: " + tournament.getName() + " da "
                                    + resource.getFilename());
                            logoTrovato = true;
                            break;
                        } catch (Exception e) {
                            logger.error("Errore nel leggere il file del logo per " + tournament.getName(), e);
                        }
                    }
                }

                if (!logoTrovato) {
                    logger.warn("⚠️ Nessun logo trovato per il torneo: " + tournament.getName()
                            + " (cercato in static/images/tournaments/)");
                }
            }
        }
    }
}
