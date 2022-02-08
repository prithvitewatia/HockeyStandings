package com.example.HockeyStandings.core.coach.web;

import com.example.HockeyStandings.core.team.web.TeamView;

public class CoachView {
    private long id;
    private String name;
    private int age,experience;
    private TeamView team;
    public long getId(){
        return id;
    }
    public void setId(long id){
        this.id=id;
    }
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
    public TeamView getTeam(){
        return team;
    }
    public void setTeam(TeamView team){
        this.team=team;
    }
}
