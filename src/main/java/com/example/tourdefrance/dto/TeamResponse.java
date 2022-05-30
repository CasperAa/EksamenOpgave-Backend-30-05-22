package com.example.tourdefrance.dto;
import com.example.tourdefrance.Entity.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TeamResponse {

    private int id;
    private String name;

    public TeamResponse(Team body){
        this.id = body.getId();
        this.name = body.getName();
    }
}
