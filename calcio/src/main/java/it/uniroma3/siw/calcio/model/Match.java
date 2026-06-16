package it.uniroma3.siw.calcio.model;

import java.time.LocalDate;
import java.time.LocalTime;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;


@Entity
public class Match {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate date;
    private LocalTime time;
    private int goalsHome;
    private int goalsAway;
    private MatchState state;

    @ManyToOne
    private Tournament tournament;
    
    @ManyToOne
    private Team homeTeams;
    
    @ManyToOne
    private Team awayTeams;
    
    @ManyToOne
    private Referee referee;
    
    
    @Override
    public String toString() {
        return "Match [id=" + id + ", date=" + date + ", time=" + time + ", homeTeams=" + homeTeams
                + ", awayTeams=" + awayTeams + ", goalsHome=" + goalsHome + ", goalsAway=" + goalsAway
                + ", state=" + state + ", tournament=" + tournament + ", referee=" + referee + "]";
    }

    //getters e setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public LocalTime getTime() {
        return time;
    }
    public void setTime(LocalTime time) {
        this.time = time;
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
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + ((time == null) ? 0 : time.hashCode());
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
        if (date == null) {
            if (other.date != null)
                return false;
        } else if (!date.equals(other.date))
            return false;
        if (time == null) {
            if (other.time != null)
                return false;
        } else if (!time.equals(other.time))
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
