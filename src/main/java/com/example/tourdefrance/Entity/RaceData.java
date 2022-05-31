package com.example.tourdefrance.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class RaceData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @ManyToOne
    Race race;

    @ManyToOne
    Cyclist cyclist;

    String duration;
    int placement;
    int points;

    public RaceData(String duration, int placement, int points, Cyclist cyclist, Race race) {
        this.duration = duration;
        this.placement = placement;
        this.points = points;
        this.race = race;
        this.cyclist = cyclist;
        cyclist.addCyclist(this);
        race.addRace(this);
    }
}
