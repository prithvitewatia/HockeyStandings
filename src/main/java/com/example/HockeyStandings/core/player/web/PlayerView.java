package com.example.HockeyStandings.core.player.web;

import com.example.HockeyStandings.core.team.web.TeamView;

public class PlayerView {
    private long id;
    private String name;
    private int age;
    private TeamView team;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public TeamView getTeam() {
        return team;
    }

    public void setTeam(TeamView team) {
        this.team = team;
    }
}
