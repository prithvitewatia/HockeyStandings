package com.example.HockeyStandings.core.match;

import com.example.HockeyStandings.core.match.converter.MatchToMatchViewConverter;
import com.example.HockeyStandings.core.match.web.MatchBaseReq;
import com.example.HockeyStandings.core.match.web.MatchView;
import com.example.HockeyStandings.core.team.TeamRepo;
import com.example.HockeyStandings.core.team.converter.TeamToTeamViewConverter;
import com.example.HockeyStandings.core.tournament.TournamentRepo;
import com.example.HockeyStandings.core.tournament.converter.TournamentToTournamentViewConverter;
import com.example.HockeyStandings.error.EntityNotFoundException;
import com.example.HockeyStandings.util.MessageUtil;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MatchService {
    private final MatchRepo matchRepo;
    private final MatchToMatchViewConverter matchToMatchViewConverter;
    private final TeamRepo teamRepo;
    private final TeamToTeamViewConverter teamToTeamViewConverter;
    private final TournamentRepo tournamentRepo;
    private final TournamentToTournamentViewConverter tournamentToTournamentViewConverter;
    private final MessageUtil messageUtil;


    public MatchService(MatchRepo matchRepo,
                        MatchToMatchViewConverter matchToMatchViewConverter,
                        TeamRepo teamRepo,
                        TeamToTeamViewConverter teamToTeamViewConverter,
                        TournamentRepo tournamentRepo,
                        TournamentToTournamentViewConverter tournamentToTournamentViewConverter,
                        MessageUtil messageUtil) {
        this.matchRepo = matchRepo;
        this.matchToMatchViewConverter = matchToMatchViewConverter;
        this.teamRepo = teamRepo;
        this.teamToTeamViewConverter = teamToTeamViewConverter;
        this.tournamentRepo = tournamentRepo;
        this.tournamentToTournamentViewConverter = tournamentToTournamentViewConverter;
        this.messageUtil = messageUtil;
    }
    public MatchView getMatch(Long id){
        Match match=findMatchOrThrow(id);
        return matchToMatchViewConverter.convert(match);
    }
    public Match findMatchOrThrow(Long id){
        return matchRepo.findById(id).orElseThrow(
                ()->new EntityNotFoundException(messageUtil.getMessage("match.NotFound",id))
        );
    }
    public Page<MatchView> findAllMatch(Pageable pageable){
        Page<Match> matches=matchRepo.findAll(pageable);
        List<MatchView> matchViewList=new ArrayList<>();
        matches.forEach(match ->{
            matchViewList.add(matchToMatchViewConverter.convert(match));
        });
        return new PageImpl<>(matchViewList,pageable,matches.getTotalElements());
    }
    private Match prepare(Match match, MatchBaseReq matchBaseReq){
        match.setMatchDate(matchBaseReq.getMatchDate());
        match.setHomeTeam(teamRepo.getOne(matchBaseReq.getHomeTeamId()));
        match.setAwayTeam(teamRepo.getOne(matchBaseReq.getAwayTeamId()));
        match.setTournament(tournamentRepo.getOne(matchBaseReq.getTourId()));
        match.setHomeScore(matchBaseReq.getHomeScore());
        match.setAwayScore(matchBaseReq.getAwayScore());
        return match;
    }
    public MatchView create(MatchBaseReq matchBaseReq){
        Match match=new Match();
        this.prepare(match,matchBaseReq);
        Match saveMatch=matchRepo.save(match);
        return matchToMatchViewConverter.convert(saveMatch);
    }
    public void delete(Long id){
        try {
            matchRepo.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new EntityNotFoundException(messageUtil.getMessage("match.NotFound",id));
        }
    }
    public MatchView update(Match match,MatchBaseReq matchBaseReq){
        Match updateMatch=this.prepare(match,matchBaseReq);
        Match saveMatch=matchRepo.save(updateMatch);
        return matchToMatchViewConverter.convert(saveMatch);
    }

}
