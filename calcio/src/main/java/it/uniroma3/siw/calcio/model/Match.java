package it.uniroma3.siw.calcio.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Match {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate data;
    private LocalTime ora;
    private int goalsHome;
    private int goalsAway;
    private MatchState state;

    @OneToOne
    private Tournament tournament;
    @ManyToOne
    private Team homeTeams;
    @ManyToOne
    private Team awayTeams;
    @ManyToOne
    private Referee referee;
    
    
    
    
    
    
    
    //getters e setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public LocalDate getData() {
        return data;
    }
    public void setData(LocalDate data) {
        this.data = data;
    }
    public LocalTime getOra() {
        return ora;
    }
    public void setOra(LocalTime ora) {
        this.ora = ora;
    }
    public int getGoalsHome() {
        return goalsHome;
    }
    public void setGoalsHome(int goalsHome) {
        this.goalsHome = goalsHome;
    }
    public int getGoalsAway() {
        return goalsAway;
    }
    public void setGoalsAway(int goalsAway) {
        this.goalsAway = goalsAway;
    }
    public MatchState getState() {
        return state;
    }
    public void setState(MatchState state) {
        this.state = state;
    }
    public Tournament getTournament() {
        return tournament;
    }
    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }
    
    public Referee getReferee() {
        return referee;
    }
    public void setReferee(Referee referee) {
        this.referee = referee;
    }
    public Team getHomeTeams() {
        return homeTeams;
    }
    public void setHomeTeams(Team homeTeams) {
        this.homeTeams = homeTeams;
    }
    public Team getAwayTeams() {
        return awayTeams;
    }
    public void setAwayTeams(Team awayTeams) {
        this.awayTeams = awayTeams;
    }
    
    
    
    //hashCode e equals
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((data == null) ? 0 : data.hashCode());
        result = prime * result + ((ora == null) ? 0 : ora.hashCode());
        result = prime * result + ((tournament == null) ? 0 : tournament.hashCode());
        result = prime * result + ((homeTeams == null) ? 0 : homeTeams.hashCode());
        result = prime * result + ((awayTeams == null) ? 0 : awayTeams.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Match other = (Match) obj;
        if (data == null) {
            if (other.data != null)
                return false;
        } else if (!data.equals(other.data))
            return false;
        if (ora == null) {
            if (other.ora != null)
                return false;
        } else if (!ora.equals(other.ora))
            return false;
        if (tournament == null) {
            if (other.tournament != null)
                return false;
        } else if (!tournament.equals(other.tournament))
            return false;
        if (homeTeams == null) {
            if (other.homeTeams != null)
                return false;
        } else if (!homeTeams.equals(other.homeTeams))
            return false;
        if (awayTeams == null) {
            if (other.awayTeams != null)
                return false;
        } else if (!awayTeams.equals(other.awayTeams))
            return false;
        return true;
    }

    
}
