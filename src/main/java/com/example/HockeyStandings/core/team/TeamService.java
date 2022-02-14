package com.example.HockeyStandings.core.team;

import com.example.HockeyStandings.core.team.converter.TeamToTeamViewConverter;
import com.example.HockeyStandings.core.team.web.TeamBaseReq;
import com.example.HockeyStandings.core.team.web.TeamView;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.HockeyStandings.util.MessageUtil;

import com.example.HockeyStandings.error.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class TeamService {
    private final TeamRepo teamRepo;
    private final TeamToTeamViewConverter teamToTeamViewConverter;
    private final MessageUtil messageUtil;

    public TeamService(TeamRepo teamRepo,
                       TeamToTeamViewConverter teamToTeamViewConverter,
                       MessageUtil messageUtil){
        this.teamRepo=teamRepo;
        this.teamToTeamViewConverter=teamToTeamViewConverter;
        this.messageUtil=messageUtil;
    }
    public TeamView getTeam(Long id){
        Team team=findTeamOrThrow(id);
        return teamToTeamViewConverter.convert(team);
    }
    public Team findTeamOrThrow(Long id){
        return teamRepo.findById(id).orElseThrow(()->new EntityNotFoundException(
                messageUtil.getMessage("team not found",id)));
    }
    public Page<TeamView> findAllTeam(Pageable pageable){
        Page<Team> teams=teamRepo.findAll(pageable);
        List<TeamView> teamViews=new ArrayList<>();
        teams.forEach(
                team -> {
                    TeamView teamView=teamToTeamViewConverter.convert(team);
                    teamViews.add(teamView);
                }
        );
        return new PageImpl<>(teamViews,pageable,teams.getTotalElements());
    }
    public TeamView create(TeamBaseReq teamBaseReq){
        Team team=new Team();
        this.prepare(team, teamBaseReq);
        Team teamSave=teamRepo.save(team);
        return teamToTeamViewConverter.convert(teamSave);
    }
    @Transactional
    public void delete(Long id){
        try{
            teamRepo.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new EntityNotFoundException(messageUtil.getMessage("team.NotFound",id));
        }
    }
    public TeamView update(Team team,TeamBaseReq teamBaseReq){
        Team updateTeam=this.prepare(team,teamBaseReq);
        Team teamSave=teamRepo.save(updateTeam);
        return teamToTeamViewConverter.convert(teamSave);
    }
    private Team prepare(Team team, TeamBaseReq teamBaseReq){
        team.setName(teamBaseReq.getName());
        team.setOwner(teamBaseReq.getOwner());
        return team;
    }
}
