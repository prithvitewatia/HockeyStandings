package com.example.HockeyStandings.core.team.web;

import com.example.HockeyStandings.base.BaseRequest;

import javax.validation.constraints.NotEmpty;

public class TeamBaseReq extends BaseRequest {
    @NotEmpty
    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String owner;
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
    public String getOwner(){
        return owner;
    }
    public void setOwner(String owner){
        this.owner=owner;
    }
}
