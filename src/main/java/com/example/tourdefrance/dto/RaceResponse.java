package com.example.tourdefrance.dto;

import com.example.tourdefrance.Entity.Race;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RaceResponse {

    private int id;
    private String name;
    private int distance;
    private String type;


    public RaceResponse(Race body) {
        this.id = body.getId();
        this.name = body.getName();
        this.distance = body.getDistance();
        this.type = body.getType();

    }
}

