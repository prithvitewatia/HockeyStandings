package com.example.HockeyStandings.core.player.converter;

import com.example.HockeyStandings.core.player.Player;
import com.example.HockeyStandings.core.player.web.PlayerView;
import com.example.HockeyStandings.core.team.converter.TeamToTeamViewConverter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PlayerToPlayerViewConverter implements Converter<Player, PlayerView> {
    private final TeamToTeamViewConverter teamToTeamViewConverter;
    public PlayerToPlayerViewConverter(TeamToTeamViewConverter teamToTeamViewConverter){
        this.teamToTeamViewConverter=teamToTeamViewConverter;
    }
    @Override
    public PlayerView convert(Player player){
        PlayerView playerView=new PlayerView();
        playerView.setId(player.getId());
        playerView.setName(player.getName());
        playerView.setAge(player.getAge());
        playerView.setTeam(teamToTeamViewConverter.convert(player.getTeam()));
        return playerView;
    }
}
