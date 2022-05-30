package com.example.tourdefrance.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @OneToMany(mappedBy = "team", cascade ={ CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Racer> racers = new HashSet<>();

    public Team(String name) {
        this.name = name;
    }

    public void addRacer(Racer racer) {
        this.racers.add(racer);
        racer.setTeam(this);
    }

    public void addRacers(Set<Racer> racers) {
        this.racers.addAll(racers);
        for (Racer racer : racers) {
            racer.setTeam(this);
        }
    }
}
