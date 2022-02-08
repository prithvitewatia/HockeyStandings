package com.example.HockeyStandings.core.coach.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import com.example.HockeyStandings.core.coach.web.CoachView;
import com.example.HockeyStandings.core.coach.Coach;
import com.example.HockeyStandings.core.team.Team;
import com.example.HockeyStandings.core.team.converter.TeamToTeamViewConverter;

import javax.validation.constraints.NotNull;

@Component
public class CoachToCoachViewConverter implements Converter<Coach,CoachView> {
    private final TeamToTeamViewConverter teamToTeamViewConverter;
    public CoachToCoachViewConverter(TeamToTeamViewConverter teamToTeamViewConverter){
        this.teamToTeamViewConverter=teamToTeamViewConverter;
    }

    @Override
    public CoachView convert(@NotNull Coach coach){
        CoachView view=new CoachView();
        view.setId(coach.getId());
        view.setAge(coach.getAge());
        view.setExperience(coach.getExperience());
        Team team=coach.getTeam();
        view.setTeam(teamToTeamViewConverter.convert(team));
        return view;
    }
}
