package com.example.tourdefrance.Entity;

import com.example.tourdefrance.dto.CyclistRequest;
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
public class Cyclist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;
    private String lastName;
    private String country;
    private int age;
    private int points;

    @ManyToOne()
    private Team team;

    @OneToMany(mappedBy = "cyclist", fetch = FetchType.EAGER)
    private Set<RaceData> raceData = new HashSet<>();

    public void addCyclist(RaceData data){
        raceData.add(data);
    }

    public Cyclist(String firstName, String lastName, String country, int age, int points) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.age = age;
        this.points = points;
    }

    public Cyclist(CyclistRequest body){
        this.firstName = body.getFirstName();
        this.lastName = body.getLastName();
        this.country = body.getCountry();
        this.age = body.getAge();
        this.points = body.getPoints();
    }

}
