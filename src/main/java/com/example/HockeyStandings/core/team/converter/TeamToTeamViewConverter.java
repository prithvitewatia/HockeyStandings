package com.example.HockeyStandings.core.team.converter;

import com.example.HockeyStandings.core.team.Team;
import com.example.HockeyStandings.core.team.web.TeamView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class TeamToTeamViewConverter implements Converter<Team, TeamView> {
    @Override
    public TeamView convert(@NotNull Team team){
        TeamView view=new TeamView();
        view.setId(team.getId());
        view.setName(team.getName());
        view.setOwner(team.getOwner());
        return view;
    }
}
