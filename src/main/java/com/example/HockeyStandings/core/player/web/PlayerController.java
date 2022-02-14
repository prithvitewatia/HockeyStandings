package com.example.HockeyStandings.core.player.web;

import com.example.HockeyStandings.core.player.Player;
import com.example.HockeyStandings.core.player.PlayerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/player")
public class PlayerController {
    private final PlayerService playerService;
    public PlayerController(PlayerService playerService){
        this.playerService=playerService;
    }
    @GetMapping("/{id}")
    @ResponseBody
    public PlayerView getPlayer(@PathVariable Long id){
        return playerService.getPlayer(id);
    }
    @GetMapping
    @ResponseBody
    public Page<PlayerView> getAllPlayer(@PageableDefault(sort = "id",direction = Sort.Direction.ASC)
                                                     Pageable pageable){
        return playerService.findAllPlayer(pageable);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public PlayerView create(@RequestBody @Valid PlayerBaseReq playerBaseReq){
        return playerService.create(playerBaseReq);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public void deletePlayer(@PathVariable Long id){
        playerService.delete(id);
    }
    @PutMapping("/{id}")
    public PlayerView updatePlayer(@PathVariable(name = "id") Long id,
                                   @RequestBody @Valid PlayerBaseReq playerBaseReq){
        Player player= playerService.findPlayerOrThrow(id);
        return playerService.update(player,playerBaseReq);
    }
}
