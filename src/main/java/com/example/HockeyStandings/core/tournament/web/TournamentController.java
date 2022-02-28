package com.example.HockeyStandings.core.tournament.web;

import com.example.HockeyStandings.core.tournament.Tournament;
import com.example.HockeyStandings.core.tournament.TournamentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/tournament")
public class TournamentController {
    private final TournamentService tournamentService;
    public TournamentController(TournamentService tournamentService){
        this.tournamentService=tournamentService;
    }
    @GetMapping("/{id}")
    @ResponseBody
    public TournamentView getTournament(@PathVariable Long id){
        return tournamentService.getTournament(id);
    }
    @GetMapping
    @ResponseBody
    public Page<TournamentView> getAllTournament(@PageableDefault(sort = "id",direction = Sort.Direction.ASC)
                                                 Pageable pageable){
        return tournamentService.findAllTournament(pageable);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public TournamentView create(@RequestBody @Valid TournamentBaseReq tournamentBaseReq){
        return tournamentService.create(tournamentBaseReq);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public void delete(@PathVariable Long id){
        tournamentService.delete(id);
    }
    @PutMapping("/{id}")
    public TournamentView update(@PathVariable(name="id") Long id,
                                 @RequestBody @Valid TournamentBaseReq tournamentBaseReq){
        Tournament tournament=tournamentService.findTournamentOrThrow(id);
        return tournamentService.update(tournament,tournamentBaseReq);
    }
}
