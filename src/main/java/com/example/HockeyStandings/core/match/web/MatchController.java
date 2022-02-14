package com.example.HockeyStandings.core.match.web;

import com.example.HockeyStandings.core.match.Match;
import com.example.HockeyStandings.core.match.MatchService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/match")
public class MatchController {
    private final MatchService matchService;
    public MatchController(MatchService matchService){
        this.matchService=matchService;
    }
    @GetMapping("/{id}")
    @ResponseBody
    public MatchView getMatch(@PathVariable Long id){
        return matchService.getMatch(id);
    }
    @GetMapping
    @ResponseBody
    public Page<MatchView> getAllMatch(@PageableDefault(sort = "id",direction = Sort.Direction.ASC)Pageable pageable){
        return matchService.findAllMatch(pageable);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public MatchView create(@RequestBody @Valid MatchBaseReq matchBaseReq){
        return matchService.create(matchBaseReq);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public void deleteMatch(@PathVariable Long id){
        matchService.delete(id);
    }
    @PutMapping("/{id}")
    public MatchView update(@PathVariable(name = "id") Long id,
                            @RequestBody @Valid MatchBaseReq matchBaseReq){
        Match match= matchService.findMatchOrThrow(id);
        return matchService.update(match,matchBaseReq);
    }
}
