package com.example.tourdefrance.dto;

import com.example.tourdefrance.Entity.Cyclist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CyclistResponse {
    private int id;
    private String firstName;
    private String lastName;
    private String country;
    private int age;
    private int points;
    private String team;

    public CyclistResponse(Cyclist body){
        this.id = body.getId();
        this.firstName = body.getFirstName();
        this.lastName = body.getLastName();
        this.country = body.getCountry();
        this.age = body.getAge();
        this.points = body.getPoints();
        this.team = body.getTeam().getName();

    }
}
