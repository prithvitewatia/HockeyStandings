package com.example.HockeyStandings.core.tournament.web;

public class TournamentView {
    private long id;
    private String name;
    private int year;
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
    public int getYear(){
        return year;
    }
    public void setYear(int year){
        this.year=year;
    }
}
