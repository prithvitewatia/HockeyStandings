package com.example.HockeyStandings.core.coach.web;

import com.example.HockeyStandings.base.BaseRequest;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CoachBaseReq extends BaseRequest {
    @NotEmpty
    private String name;
    @NotNull
    private int age;
    @NotNull
    private int experience;
    private Long teamId;

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public int getAge(){
        return age;
    }
    public void setAge(int age){
        this.age=age;
    }
    public int getExperience(){
        return experience;
    }
    public void setExperience(int experience){
        this.experience=experience;
    }
    public Long getTeamId(){
        return teamId;
    }
    public void setTeamId(Long teamId){
        this.teamId=teamId;
    }
}
