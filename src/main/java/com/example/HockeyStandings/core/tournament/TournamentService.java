package com.example.HockeyStandings.core.tournament;

import com.example.HockeyStandings.core.tournament.converter.TournamentToTournamentViewConverter;
import com.example.HockeyStandings.core.tournament.web.TournamentBaseReq;
import com.example.HockeyStandings.core.tournament.web.TournamentView;
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
public class TournamentService {
    private final TournamentRepo tournamentRepo;
    private final TournamentToTournamentViewConverter tournamentToTournamentViewConverter;
    private final MessageUtil messageUtil;

    public TournamentService(TournamentRepo tournamentRepo,
                             TournamentToTournamentViewConverter tournamentToTournamentViewConverter,
                             MessageUtil messageUtil){
        this.tournamentRepo=tournamentRepo;
        this.tournamentToTournamentViewConverter=tournamentToTournamentViewConverter;
        this.messageUtil=messageUtil;
    }
    public TournamentView getTournament(Long id){
        Tournament tournament=findTournamentOrThrow(id);
        return tournamentToTournamentViewConverter.convert(tournament);
    }
    public Tournament findTournamentOrThrow(Long id){
        return tournamentRepo.findById(id).orElseThrow(()->new EntityNotFoundException(
                messageUtil.getMessage("tournament.not found",id)
        ));
    }
    public Page<TournamentView> findAllTournament(Pageable pageable){
        Page<Tournament> tournaments=tournamentRepo.findAll(pageable);
        List<TournamentView> tournamentViewList=new ArrayList<>();
        tournaments.forEach(tournament -> {
            tournamentViewList.add(tournamentToTournamentViewConverter.convert(tournament));
        });
        return new PageImpl<>(tournamentViewList,pageable,tournaments.getTotalElements());
    }
    public Tournament prepare(Tournament tournament,TournamentBaseReq tournamentBaseReq){
        tournament.setName(tournamentBaseReq.getName());
        tournament.setYear(tournamentBaseReq.getYear());
        return tournament;
    }
    public TournamentView create(TournamentBaseReq tournamentBaseReq){
        Tournament tournament=new Tournament();
        this.prepare(tournament,tournamentBaseReq);
        Tournament saveTournament=tournamentRepo.save(tournament);
        return tournamentToTournamentViewConverter.convert(saveTournament);
    }
    @Transactional
    public void delete(Long id){
        try{
            tournamentRepo.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new EntityNotFoundException(messageUtil.getMessage("tournament.not found",id));
        }
    }
    public TournamentView update(Tournament tournament,TournamentBaseReq tournamentBaseReq){
        Tournament updatedTournament=this.prepare(tournament,tournamentBaseReq);
        Tournament saveTournament=tournamentRepo.save(updatedTournament);
        return tournamentToTournamentViewConverter.convert(saveTournament);
    }
}
