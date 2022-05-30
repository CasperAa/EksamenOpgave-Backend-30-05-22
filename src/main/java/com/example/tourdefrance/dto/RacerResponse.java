package com.example.tourdefrance.dto;

import com.example.tourdefrance.Entity.Racer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RacerResponse {
    private int id;
    private String firstName;
    private String lastName;
    private String country;
    private int age;
    private int points;

    public RacerResponse(Racer body){
        this.id = body.getId();
        this.firstName = body.getFirstName();
        this.lastName = body.getLastName();
        this.country = body.getCountry();
        this.age = body.getAge();
        this.points = getPoints();
    }

}
