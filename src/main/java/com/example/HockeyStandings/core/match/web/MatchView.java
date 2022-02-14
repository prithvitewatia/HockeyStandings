package com.example.HockeyStandings.core.match.web;

import com.example.HockeyStandings.core.team.web.TeamView;
import com.example.HockeyStandings.core.tournament.web.TournamentView;

import java.time.LocalDate;

public class MatchView {
    private long id;
    private LocalDate matchDate;
    private TeamView homeView,awayView;
    private TournamentView tournamentView;
    private int homeScore,awayScore;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(LocalDate matchDate) {
        this.matchDate = matchDate;
    }

    public TeamView getHomeView() {
        return homeView;
    }

    public void setHomeView(TeamView homeView) {
        this.homeView = homeView;
    }

    public TeamView getAwayView() {
        return awayView;
    }

    public void setAwayView(TeamView awayView) {
        this.awayView = awayView;
    }

    public TournamentView getTournamentView() {
        return tournamentView;
    }

    public void setTournamentView(TournamentView tournamentView) {
        this.tournamentView = tournamentView;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(int awayScore) {
        this.awayScore = awayScore;
    }
}
