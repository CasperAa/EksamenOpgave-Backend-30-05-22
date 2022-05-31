package com.example.tourdefrance.Entity;

import com.example.tourdefrance.dto.RaceRequest;
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
public class Race {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String type;
    private int distance;


    @OneToMany(mappedBy = "race", fetch = FetchType.EAGER)
    private Set<RaceData> raceData = new HashSet<>();

    public void addRace(RaceData data){
        raceData.add(data);
    }


    public Race(String name, String type, int distance) {
        this.name = name;
        this.type = type;
        this.distance = distance;

    }
    public Race(RaceRequest body) {
        this.name = body.getName();
        this.type = body.getType();
        this.distance = body.getDistance();


    }
}
