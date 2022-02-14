package com.example.HockeyStandings.core.player;

import com.example.HockeyStandings.core.player.converter.PlayerToPlayerViewConverter;
import com.example.HockeyStandings.core.player.web.PlayerBaseReq;
import com.example.HockeyStandings.core.player.web.PlayerView;
import com.example.HockeyStandings.core.team.TeamRepo;
import com.example.HockeyStandings.error.EntityNotFoundException;
import com.example.HockeyStandings.util.MessageUtil;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerService {
    private final PlayerRepo playerRepo;
    private final TeamRepo teamRepo;
    private final PlayerToPlayerViewConverter playerToPlayerViewConverter;
    private final MessageUtil messageUtil;

    public PlayerService(PlayerRepo playerRepo,
                         TeamRepo teamRepo,
                         PlayerToPlayerViewConverter playerToPlayerViewConverter,
                         MessageUtil messageUtil) {
        this.playerRepo = playerRepo;
        this.teamRepo = teamRepo;
        this.playerToPlayerViewConverter = playerToPlayerViewConverter;
        this.messageUtil = messageUtil;
    }
    public PlayerView getPlayer(Long id){
        Player player=findPlayerOrThrow(id);
        return playerToPlayerViewConverter.convert(player);
    }
    public Player findPlayerOrThrow(Long id){
        return playerRepo.findById(id).orElseThrow(
                ()->new EntityNotFoundException(messageUtil.getMessage("player.NotFound",id)));
    }
    public Page<PlayerView> findAllPlayer(Pageable pageable){
        Page<Player> players=playerRepo.findAll(pageable);
        List<PlayerView> playerViewList=new ArrayList<>();
        players.forEach(player -> {
            playerViewList.add(playerToPlayerViewConverter.convert(player));
                }
        );
        return new PageImpl<>(playerViewList,pageable,players.getTotalElements());
    }
    private Player prepare(Player player, PlayerBaseReq playerBaseReq){
        player.setName(playerBaseReq.getName());
        player.setAge(playerBaseReq.getAge());
        player.setTeam(teamRepo.getOne(playerBaseReq.getTeam()));
        return player;
    }
    public PlayerView create(PlayerBaseReq playerBaseReq){
        Player player=new Player();
        this.prepare(player,playerBaseReq);
        Player playerSave=playerRepo.save(player);
        return playerToPlayerViewConverter.convert(playerSave);
    }
    @Transactional
    public void delete(Long id){
        try{
            playerRepo.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new EntityNotFoundException(messageUtil.getMessage("player.NotFound"));
        }
    }
    public PlayerView update(Player player,PlayerBaseReq playerBaseReq){
        Player updatePlayer=this.prepare(player,playerBaseReq);
        Player savePlayer=playerRepo.save(updatePlayer);
        return playerToPlayerViewConverter.convert(savePlayer);
    }
}
