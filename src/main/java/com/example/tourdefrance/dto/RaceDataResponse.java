package com.example.tourdefrance.dto;

import com.example.tourdefrance.Entity.RaceData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RaceDataResponse {

    private int id;
    private String duration;
    private int placement;
    private int points;
    private String raceName;
    private String cyclistFirstName;
    private String cyclistLastName;
    private String team;


    public RaceDataResponse(RaceData body) {
        this.id = body.getId();
        this.duration = body.getDuration();
        this.placement = body.getPlacement();
        this.points = body.getPoints();
        this.raceName = body.getRace().getName();
        this.cyclistFirstName = body.getCyclist().getFirstName();
        this.cyclistLastName = body.getCyclist().getFirstName();
        this.team = body.getCyclist().getTeam().getName();

    }
}
