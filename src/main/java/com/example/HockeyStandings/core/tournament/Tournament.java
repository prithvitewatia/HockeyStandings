package com.example.HockeyStandings.core.tournament;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "hockey_tournament")
public class Tournament {
    @Id
    @Column(name = "id")
    @GenericGenerator(
            name="hockey_tournament_id_seq",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name",value = "hockey_tournament_id_seq"),
                    @org.hibernate.annotations.Parameter(name = "INCREMENT", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "MINVALUE", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "MAXVALUE", value = "2147483647"),
                    @org.hibernate.annotations.Parameter(name = "CACHE", value = "1")
            }
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hockey_tournament_id_seq")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "year")
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
