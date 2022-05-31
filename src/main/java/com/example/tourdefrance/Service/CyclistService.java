package com.example.tourdefrance.Service;

import com.example.tourdefrance.Entity.Cyclist;
import com.example.tourdefrance.Entity.Team;
import com.example.tourdefrance.Error.Client4xxException;
import com.example.tourdefrance.Repository.CyclistRepository;
import com.example.tourdefrance.Repository.TeamRepository;
import com.example.tourdefrance.dto.CyclistRequest;
import com.example.tourdefrance.dto.CyclistResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CyclistService {

    CyclistRepository cyclistRepository;
    TeamRepository teamRepository;

    public CyclistService(CyclistRepository cyclistRepository, TeamRepository teamRepository){
        this.cyclistRepository = cyclistRepository;
        this.teamRepository = teamRepository;
    }

    //Returns all Racers, if teamName is added it returns racers based on that input
    public List<CyclistResponse> getAllCyclists(String teamName){
        List <Cyclist> cyclists;
        if(teamName != null){
            cyclists = cyclistRepository.findCyclistByTeam_Name(teamName);
        } else {
            cyclists = cyclistRepository.findAll();
        }
        return cyclists.stream().map(CyclistResponse::new).collect(Collectors.toList());
    }

    //Returns one Racer based on it's ID
    public CyclistResponse getCyclist(int id) throws Exception {
        Cyclist cyclist = cyclistRepository.findById(id).orElseThrow(()->new Client4xxException("No racer with that id exists"));
        return new CyclistResponse(cyclist);
    }

    //Adds new racer to DB
    public CyclistResponse addCyclist(CyclistRequest body, int teamId) throws Exception{
        Team team = teamRepository.findById(teamId).orElseThrow(()->new Client4xxException("No racer with that id exists"));
        Cyclist cyclistToAdd = new Cyclist(body);
        team.addRacer(cyclistToAdd);
        teamRepository.save(team);
        return new CyclistResponse(cyclistRepository.save(cyclistToAdd));
    }

    //Edit all Racer attributes
    public CyclistResponse editCyclist(CyclistRequest editedRacer, int id){
        Cyclist cyclist = cyclistRepository.findById(id).orElseThrow(()-> new Client4xxException("No racer with provided ID found"));
        cyclist.setFirstName(editedRacer.getFirstName());
        cyclist.setLastName(editedRacer.getLastName());
        cyclist.setCountry(editedRacer.getCountry());
        cyclist.setAge(editedRacer.getAge());
        cyclist.setPoints(editedRacer.getPoints());
        return new CyclistResponse(cyclistRepository.save(cyclist));
    }

    //Deletes a racer based on ID
    public void deleteCyclist(int id){
        cyclistRepository.deleteById(id);
    }

}
