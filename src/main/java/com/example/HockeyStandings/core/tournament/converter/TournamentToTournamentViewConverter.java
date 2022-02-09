package com.example.HockeyStandings.core.tournament.converter;

import com.example.HockeyStandings.core.tournament.Tournament;
import com.example.HockeyStandings.core.tournament.web.TournamentView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TournamentToTournamentViewConverter implements Converter<Tournament, TournamentView> {
    @Override
    public TournamentView convert(Tournament tournament){
        TournamentView view=new TournamentView();
        view.setId(tournament.getId());
        view.setName(tournament.getName());
        view.setYear(tournament.getYear());
        return view;
    }
}