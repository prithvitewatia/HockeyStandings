package com.example.HockeyStandings.core.coach;

import com.example.HockeyStandings.core.team.Team;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

@Entity
@Table(name="hockey_coach")
public class Coach {
    @Id
    @Column(name="id")
    @GenericGenerator(
            name = "hockey_coach_id_seq",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name="sequence_name",value="hockey_coach_id_seq"),
                    @org.hibernate.annotations.Parameter(name="INCREMENT",value = "1"),
                    @org.hibernate.annotations.Parameter(name="MINVALUE", value = "1"),
                    @org.hibernate.annotations.Parameter(name="MAXVALUE", value = "2147483647"),
                    @org.hibernate.annotations.Parameter(name = "CACHE", value = "1")
            }
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "hockey_coach_id_seq")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private int age;
    @Column(name = "experience")
    private int experience;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teamid")
    private Team team;

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
    public Team getTeam(){
        return team;
    }
    public void setTeam(Team team){
        this.team=team;
    }
}
