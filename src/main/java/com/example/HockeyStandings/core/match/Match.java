package com.example.HockeyStandings.core.match;

import com.example.HockeyStandings.core.team.Team;
import com.example.HockeyStandings.core.tournament.Tournament;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "hockey_match")
public class Match {
    @Id
    @Column(name = "id")
    @GenericGenerator(
            name = "hockey_match_id_seq",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name",value = "hockey_match_id_seq"),
                    @org.hibernate.annotations.Parameter(name = "INCREMENT",value = "1"),
                    @org.hibernate.annotations.Parameter(name = "MINVALUE",value = "1"),
                    @org.hibernate.annotations.Parameter(name = "MAXVALUE",value = "2147483647"),
                    @org.hibernate.annotations.Parameter(name = "CACHE",value = "1")
            }
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "hockey_match_id_seq")
    private Long id;
    @Column(name = "match_date")
    private LocalDate matchDate;
    @Column(name = "homeId")
    @JoinColumn(name = "teamId")
    private Team homeTeam;
    @Column(name = "awayId")
    @JoinColumn(name = "teamId")
    private Team awayTeam;
    @Column(name = "tourId")
    @JoinColumn(name = "id")
    private Tournament tournament;
    @Column(name = "scoreHome")
    private int homeScore;
    @Column(name = "scoreAway")
    private int awayScore;
    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id=id;
    }
    public LocalDate getMatchDate(){
        return matchDate;
    }
    public void setMatchDate(LocalDate matchDate){
        this.matchDate=matchDate;
    }
    public Tournament getTournament(){
        return tournament;
    }
    public void setTournament(Tournament tournament){
        this.tournament=tournament;
    }
    public Team getHomeTeam(){
        return homeTeam;
    }
    public void setHomeTeam(Team homeTeam){
        this.homeTeam=homeTeam;
    }
    public Team getAwayTeam(){
        return awayTeam;
    }
    public void setAwayTeam(Team awayTeam){
        this.awayTeam=awayTeam;
    }
    public int getHomeScore(){
        return homeScore;
    }
    public void setHomeScore(int homeScore){
        this.homeScore=homeScore;
    }
    public int getAwayScore(){
        return awayScore;
    }
    public void setAwayScore(int awayScore){
        this.awayScore=awayScore;
    }
}
