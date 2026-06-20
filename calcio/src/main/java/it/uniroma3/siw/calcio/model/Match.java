package it.uniroma3.siw.calcio.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

@Entity
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private LocalDate date;
    @NotNull
    private LocalTime time;

    private int goalsHome;
    private int goalsAway;

    @Enumerated(EnumType.STRING)
    private MatchState state;

    @ManyToOne
    private Tournament tournament;

    @NotNull
    @ManyToOne
    private Team homeTeam;

    @NotNull
    @ManyToOne
    private Team awayTeam;

    @ManyToOne
    private Referee referee;

    // diverso da Ricc!!!!!!!!!!!!!!!!!!!!!
    @Override
    public String toString() {
        return "Match [id=" + id + ", date=" + date + ", time=" + time + ", homeTeam=" + homeTeam
                + ", awayTeam=" + awayTeam + ", goalsHome=" + goalsHome + ", goalsAway=" + goalsAway
                + ", state=" + state + ", tournament=" + tournament + ", referee=" + referee + "]";
    }

    // getters e setters
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

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    // hashCode e equals
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + ((time == null) ? 0 : time.hashCode());
        result = prime * result + ((tournament == null) ? 0 : tournament.hashCode());
        result = prime * result + ((homeTeam == null) ? 0 : homeTeam.hashCode());
        result = prime * result + ((awayTeam == null) ? 0 : awayTeam.hashCode());
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
        if (homeTeam == null) {
            if (other.homeTeam != null)
                return false;
        } else if (!homeTeam.equals(other.homeTeam))
            return false;
        if (awayTeam == null) {
            if (other.awayTeam != null)
                return false;
        } else if (!awayTeam.equals(other.awayTeam))
            return false;
        return true;
    }

}
