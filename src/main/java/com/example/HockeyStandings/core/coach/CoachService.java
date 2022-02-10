package com.example.HockeyStandings.core.coach;

import com.example.HockeyStandings.core.coach.converter.CoachToCoachViewConverter;
import com.example.HockeyStandings.core.coach.web.CoachBaseReq;
import com.example.HockeyStandings.core.coach.web.CoachView;
import com.example.HockeyStandings.core.team.TeamRepo;
import com.example.HockeyStandings.util.MessageUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.dao.EmptyResultDataAccessException;
import javax.transaction.Transactional;
import com.example.HockeyStandings.error.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoachService {

    private final CoachRepo coachRepo;
    private final CoachToCoachViewConverter coachToCoachViewConverter;
    private final TeamRepo teamRepo;
    private final MessageUtil messageUtil;
    public CoachService(CoachRepo coachRepo,
                        CoachToCoachViewConverter coachToCoachViewConverter,
                        TeamRepo teamRepo,
                        MessageUtil messageUtil){
        this.coachRepo=coachRepo;
        this.coachToCoachViewConverter=coachToCoachViewConverter;
        this.teamRepo=teamRepo;
        this.messageUtil=messageUtil;
    }
    public CoachView getCoach(Long id){
        Coach coach=findCoachOrThrow(id);
        return coachToCoachViewConverter.convert(coach);
    }
    public Coach findCoachOrThrow(Long id){
        return coachRepo.findById(id)
                .orElseThrow(()->new EntityNotFoundException(messageUtil.getMessage("coach.not found",id)));
    }
    public Page<CoachView> findAllCoach(Pageable pageable){
        Page<Coach> coaches=coachRepo.findAll(pageable);
        List<CoachView> coachViews=new ArrayList<>();
        coaches.forEach(coach -> {
            CoachView coachView= coachToCoachViewConverter.convert(coach);
            coachViews.add(coachView);
                }
        );
        return new PageImpl<>(coachViews,pageable,coaches.getTotalElements());
    }
    private Coach prepare(Coach coach, CoachBaseReq coachBaseReq){
        coach.setName(coachBaseReq.getName());
        coach.setAge(coachBaseReq.getAge());
        coach.setExperience(coachBaseReq.getExperience());
        coach.setTeam(teamRepo.getOne(coachBaseReq.getTeamId()));
        return coach;
    }
    public CoachView create(CoachBaseReq coachBaseReq){
        Coach coach=new Coach();
        this.prepare(coach,coachBaseReq);
        Coach coachSave=coachRepo.save(coach);
        return coachToCoachViewConverter.convert(coachSave);
    }
    @Transactional
    public void delete(Long id){
        try{
            coachRepo.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new EntityNotFoundException(messageUtil.getMessage("coach.notFound",id));
        }
    }
    public CoachView update(Coach coach,CoachBaseReq coachBaseReq){
        Coach updateCoach=this.prepare(coach,coachBaseReq);
        Coach coachSave=coachRepo.save(updateCoach);
        return coachToCoachViewConverter.convert(coachSave);
    }
}
