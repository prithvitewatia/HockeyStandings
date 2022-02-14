package com.example.HockeyStandings.core.match.converter;

import com.example.HockeyStandings.core.match.Match;
import com.example.HockeyStandings.core.match.web.MatchView;
import com.example.HockeyStandings.core.team.converter.TeamToTeamViewConverter;
import com.example.HockeyStandings.core.tournament.converter.TournamentToTournamentViewConverter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class MatchToMatchViewConverter implements Converter<Match, MatchView> {
    private final TeamToTeamViewConverter teamToTeamViewConverter;
    private final TournamentToTournamentViewConverter tournamentToTournamentViewConverter;
    public MatchToMatchViewConverter(TeamToTeamViewConverter teamToTeamViewConverter,
                                     TournamentToTournamentViewConverter tournamentToTournamentViewConverter){
        this.tournamentToTournamentViewConverter=tournamentToTournamentViewConverter;
        this.teamToTeamViewConverter=teamToTeamViewConverter;
    }

    @Override
    public MatchView convert(@NotNull Match match){
        MatchView matchView=new MatchView();
        matchView.setId(match.getId());
        matchView.setMatchDate(match.getMatchDate());
        matchView.setHomeView(teamToTeamViewConverter.convert(match.getHomeTeam()));
        matchView.setAwayView(teamToTeamViewConverter.convert(match.getAwayTeam()));
        matchView.setTournamentView(tournamentToTournamentViewConverter.convert(match.getTournament()));
        matchView.setHomeScore(match.getHomeScore());
        matchView.setAwayScore(match.getAwayScore());
        return matchView;
    }
}
