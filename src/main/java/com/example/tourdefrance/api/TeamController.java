package com.example.tourdefrance.api;

import com.example.tourdefrance.Service.TeamService;
import com.example.tourdefrance.dto.TeamResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RequestMapping("/api/teams")
public class TeamController {

    TeamService teamService;

    public TeamController (TeamService teamService){
        this.teamService = teamService;
    }

    //Get all racers or all racers based on team
    @GetMapping
    public List<TeamResponse> getAllTeams () {
        return teamService.getAllTeams();
    }
}
