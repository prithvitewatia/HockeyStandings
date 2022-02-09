package com.example.HockeyStandings.core.tournament.web;

import com.example.HockeyStandings.base.BaseRequest;

import javax.validation.constraints.NotEmpty;

public class TournamentBaseReq extends BaseRequest {
    @NotEmpty
    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private int year;
    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id=id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public int getYear(){
        return year;
    }
    public void setYear(int year){
        this.year=year;
    }
}
