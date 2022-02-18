package com.example.HockeyStandings.core.coach.web;

import com.example.HockeyStandings.core.coach.Coach;
import com.example.HockeyStandings.core.coach.CoachService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/coach")
public class CoachController {
    private final CoachService service;
    public CoachController(CoachService service){
        this.service=service;
    }
    @GetMapping("/{id}")
    @ResponseBody
    public CoachView getCoach(@PathVariable Long id){
        return service.getCoach(id);
    }
    @GetMapping
    @ResponseBody
    public Page<CoachView> getAllCoaches(@PageableDefault(sort="id",direction=Sort.Direction.ASC) Pageable pageable){
        return service.findAllCoach(pageable);
    }
    @PostMapping(consumes = {"application/json"},produces = {"application/json"})
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public CoachView create(@RequestBody @Valid CoachBaseReq req){
        return service.create(req);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCoach(@PathVariable Long id){
        service.delete(id);
    }
    @PutMapping(path = "/{id}",consumes = {"application/json"},produces = {"application/json"})
    public CoachView updateCoach(@PathVariable(name="id") Long id,
                                 @RequestBody @Valid CoachBaseReq req){
        Coach coach= service.findCoachOrThrow(id);
        return service.update(coach,req);
    }
}
