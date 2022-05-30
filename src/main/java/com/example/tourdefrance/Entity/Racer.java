package com.example.tourdefrance.Entity;

import com.example.tourdefrance.dto.RacerRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Racer {

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

    public Racer(String firstName, String lastName, String country, int age, int points) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.age = age;
        this.points = points;
    }

    public Racer(RacerRequest body){
        this.firstName = body.getFirstName();
        this.lastName = body.getLastName();
        this.country = body.getCountry();
        this.age = body.getAge();
        this.points = body.getPoints();
    }
}
