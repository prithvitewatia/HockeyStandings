package com.example.HockeyStandings.core.player;

import com.example.HockeyStandings.core.team.Team;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "hockey_players")
public class Player {
    @Id
    @Column(name = "id")
    @GenericGenerator(
            name = "hockey_players_id_seq",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name",value = "hockey_players_id_seq"),
                    @org.hibernate.annotations.Parameter(name = "INCREMENT",value = "1"),
                    @org.hibernate.annotations.Parameter(name = "MINVALUE",value = "1"),
                    @org.hibernate.annotations.Parameter(name = "MAXVALUE",value = "2147483647"),
                    @org.hibernate.annotations.Parameter(name = "CACHE",value = "1"),
            }
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "hockey_players_id_seq")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private int age;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teamid")
    private Team team;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
