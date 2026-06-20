package it.uniroma3.siw.calcio.bootstrap;

import it.uniroma3.siw.calcio.model.Team;
import it.uniroma3.siw.calcio.repository.TeamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.Optional;

@Component
public class TeamImageSeeder implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(TeamImageSeeder.class);
    private final TeamRepository teamRepository;

    public TeamImageSeeder(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        logger.info("Avvio seeding immagini per le squadre...");
        try {
            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            Resource[] resources = resolver.getResources("classpath:static/images/teams/*.*");

            for (Resource resource : resources) {
                if (resource.isReadable()) {
                    String filename = resource.getFilename();
                    if (filename != null) {
                        String teamName = filename.substring(0, filename.lastIndexOf('.'));
                        
                        Optional<Team> teamOpt = teamRepository.findByNameIgnoreCase(teamName);
                        if (teamOpt.isPresent()) {
                            Team team = teamOpt.get();
                            if (team.getLogo() == null || team.getLogo().isEmpty()) {
                                byte[] fileContent = resource.getInputStream().readAllBytes();
                                String base64Image = Base64.getEncoder().encodeToString(fileContent);
                                
                                String contentType = "image/png"; // Default fallback
                                try {
                                    String probedType = Files.probeContentType(resource.getFile().toPath());
                                    if (probedType != null) {
                                        contentType = probedType;
                                    }
                                } catch (Exception e) {
                                    // Ignorare l'eccezione se il path non è un file reale ma dentro un jar
                                    // in locale durante lo sviluppo funzionerà
                                }
                                
                                team.setLogo("data:" + contentType + ";base64," + base64Image);
                                teamRepository.save(team);
                                logger.info("Logo impostato per la squadra: " + team.getName());
                            }
                        } else {
                            logger.warn("Nessuna squadra trovata con il nome: " + teamName + " per l'immagine " + filename);
                        }
                    }
                }
            }
        } catch (IOException e) {
            logger.error("Errore durante la lettura delle immagini delle squadre per il seeding", e);
        }
    }
}
