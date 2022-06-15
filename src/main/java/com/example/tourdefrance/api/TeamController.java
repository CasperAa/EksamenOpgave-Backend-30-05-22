package com.example.tourdefrance.api;

import com.example.tourdefrance.Service.TeamService;
import com.example.tourdefrance.dto.TeamResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin
@RequestMapping("/api/teams")
public class TeamController {

    TeamService teamService;

    public TeamController (TeamService teamService){
        this.teamService = teamService;
    }

    //Get all cyclists or all cyclists based on team
    @GetMapping
    public List<TeamResponse> getAllTeams () {
        return teamService.getAllTeams();
    }
}
