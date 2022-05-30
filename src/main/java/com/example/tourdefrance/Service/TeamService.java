package com.example.tourdefrance.Service;

import com.example.tourdefrance.Entity.Racer;
import com.example.tourdefrance.Entity.Team;
import com.example.tourdefrance.Repository.RacerRepository;
import com.example.tourdefrance.Repository.TeamRepository;
import com.example.tourdefrance.dto.RacerResponse;
import com.example.tourdefrance.dto.TeamResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamService {

    RacerRepository racerRepository;
    TeamRepository teamRepository;

    public TeamService(RacerRepository racerRepository, TeamRepository teamRepository) {
        this.racerRepository = racerRepository;
        this.teamRepository = teamRepository;
    }

    //Returns all Teams
    public List<TeamResponse> getAllTeams() {
        List<Team> teams = teamRepository.findAll();
        return teams.stream().map((team) -> new TeamResponse(team)).collect(Collectors.toList());
    }
}