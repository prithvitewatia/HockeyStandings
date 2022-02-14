package com.example.HockeyStandings.core.player.web;

import com.example.HockeyStandings.base.BaseRequest;

import javax.validation.constraints.NotEmpty;

public class PlayerBaseReq extends BaseRequest {
    @NotEmpty
    private String name;
    @NotEmpty
    private int age;
    private Long team;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Long getTeam() {
        return team;
    }

    public void setTeam(Long team) {
        this.team = team;
    }
}
