package com.example.HockeyStandings.core.team;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "hockey_team")
public class Team {
    @Id
    @Column(name = "id")
    @GenericGenerator(
            name="hockey_team_id_seq",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name="sequence_name",value = "hockey_team_id_seq"),
                    @org.hibernate.annotations.Parameter(name="INCREMENT",value = "1"),
                    @org.hibernate.annotations.Parameter(name="MINVALUE",value = "1"),
                    @org.hibernate.annotations.Parameter(name="MAXVALUE",value = "2147483647"),
                    @org.hibernate.annotations.Parameter(name="CACHE",value = "1")
            }
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "hockey_team_id_seq")
    private long id;
    @Column(name="name")
    private String name;
    @Column(name="owner")
    private String owner;

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
    public String getOwner(){
        return owner;
    }
    public void setOwner(String owner){
        this.owner=owner;
    }
}
