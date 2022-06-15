package com.example.tourdefrance.Service;

import com.example.tourdefrance.Entity.Cyclist;
import com.example.tourdefrance.Entity.RaceData;
import com.example.tourdefrance.Entity.Team;
import com.example.tourdefrance.Error.Client4xxException;
import com.example.tourdefrance.Repository.CyclistRepository;
import com.example.tourdefrance.Repository.RaceDataRepository;
import com.example.tourdefrance.Repository.TeamRepository;
import com.example.tourdefrance.dto.CyclistRequest;
import com.example.tourdefrance.dto.CyclistResponse;
import com.example.tourdefrance.dto.RaceDataResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CyclistService {

    CyclistRepository cyclistRepository;
    TeamRepository teamRepository;
    RaceDataRepository raceDataRepository;

    public CyclistService(CyclistRepository cyclistRepository, TeamRepository teamRepository, RaceDataRepository raceDataRepository){
        this.cyclistRepository = cyclistRepository;
        this.teamRepository = teamRepository;
        this.raceDataRepository = raceDataRepository;
    }

    //Returns all cyclists, if teamName is added it returns cyclists based on that input
    public List<CyclistResponse> getAllCyclists(String teamName){
        List <Cyclist> cyclists;
        if(teamName != null){
            cyclists = cyclistRepository.findCyclistByTeam_Name(teamName);
        } else {
            cyclists = cyclistRepository.findAll();
        }
        return cyclists.stream().map(CyclistResponse::new).collect(Collectors.toList());
    }

    //Returns one cyclist based on it's ID
    public CyclistResponse getCyclist(int id) throws Exception {
        Cyclist cyclist = cyclistRepository.findById(id).orElseThrow(()->new Client4xxException("No cyclist with that id exists"));
        return new CyclistResponse(cyclist);
    }

    //Adds new cyclist to DB
    public CyclistResponse addCyclist(CyclistRequest body, int teamId) throws Exception{
        Team team = teamRepository.findById(teamId).orElseThrow(()->new Client4xxException("No cyclist with that id exists"));
        Cyclist cyclistToAdd = new Cyclist(body);
        team.addCyclist(cyclistToAdd);
        //teamRepository.save(team);
        return new CyclistResponse(cyclistRepository.save(cyclistToAdd));
    }

    //Edit all cyclist attributes
    public CyclistResponse editCyclist(CyclistRequest cyclistRequest, int id){
        Cyclist cyclist = cyclistRepository.findById(id).orElseThrow(()-> new Client4xxException("No cyclist with provided ID found"));
        cyclist.setFirstName(cyclistRequest.getFirstName());
        cyclist.setLastName(cyclistRequest.getLastName());
        cyclist.setCountry(cyclistRequest.getCountry());
        cyclist.setAge(cyclistRequest.getAge());
        cyclist.setPoints(cyclistRequest.getPoints());
        return new CyclistResponse(cyclistRepository.save(cyclist));
    }

    //Deletes a cyclist based on ID
    public void deleteCyclist(int id){
        //raceDataRepository.deleteByCyclist_Id(id);
        cyclistRepository.deleteById(id);
    }

}
