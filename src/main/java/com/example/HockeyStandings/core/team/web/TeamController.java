package com.example.HockeyStandings.core.team.web;

import com.example.HockeyStandings.core.team.Team;
import com.example.HockeyStandings.core.team.TeamService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.web.PageableDefault;

import javax.validation.Valid;

@RestController
@RequestMapping("/team")
public class TeamController {
    private final TeamService teamService;
    public TeamController(TeamService teamService){
        this.teamService=teamService;
    }
    @GetMapping("/{id}")
    @ResponseBody
    public TeamView getTeam(@PathVariable Long id){
        return teamService.getTeam(id);
    }
    @GetMapping
    @ResponseBody
    public Page<TeamView> getAllTeam(@PageableDefault(sort="id",direction= Sort.Direction.ASC) Pageable pageable){
        return teamService.findAllTeam(pageable);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public TeamView create(@RequestBody @Valid TeamBaseReq teamBaseReq){
        return teamService.create(teamBaseReq);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTeam(@PathVariable Long id){
        teamService.delete(id);
    }
    @PutMapping("/{id}")
    public TeamView update(@PathVariable(name = "id") Long id,
                           @RequestBody @Valid TeamBaseReq teamBaseReq){
        Team team=teamService.findTeamOrThrow(id);
        return teamService.update(team,teamBaseReq);
    }
}
