package com.example.tourdefrance.Service;

import com.example.tourdefrance.Entity.Racer;
import com.example.tourdefrance.Entity.Team;
import com.example.tourdefrance.Error.Client4xxException;
import com.example.tourdefrance.Repository.RacerRepository;
import com.example.tourdefrance.Repository.TeamRepository;
import com.example.tourdefrance.dto.RacerRequest;
import com.example.tourdefrance.dto.RacerResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RacerService {

    RacerRepository racerRepository;
    TeamRepository teamRepository;

    public RacerService(RacerRepository racerRepository, TeamRepository teamRepository){
        this.racerRepository = racerRepository;
        this.teamRepository = teamRepository;
    }

    //Returns all Racers, if teamName is added it returns racers based on that input
    public List<RacerResponse> getAllRacers(String teamName){
        List <Racer> racers;
        if(teamName != null){
            racers = racerRepository.findRacerByTeam_Name(teamName);
        } else {
            racers = racerRepository.findAll();
        }
        return racers.stream().map((racer) -> new RacerResponse(racer)).collect(Collectors.toList());
    }

    //Returns one Racer based on it's ID
    public RacerResponse getRacer(int id) throws Exception {
        Racer racer = racerRepository.findById(id).orElseThrow(()->new Client4xxException("No racer with that id exists"));
        return new RacerResponse(racer);
    }

    //Adds new racer to DB
    public RacerResponse addRacer(RacerRequest body, int teamId) throws Exception{
        Team team = teamRepository.findById(teamId).orElseThrow(()->new Client4xxException("No racer with that id exists"));
        Racer racerToAdd = new Racer(body);
        team.addRacer(racerToAdd);
        teamRepository.save(team);
        return new RacerResponse(racerToAdd);
    }

    //Edit all Racer attributes
    public RacerResponse editRacer(RacerRequest editedRacer, int id){
        Racer racer = racerRepository.findById(id).orElseThrow(()-> new Client4xxException("No racer with provided ID found"));
        racer.setFirstName(editedRacer.getFirstName());
        racer.setLastName(editedRacer.getLastName());
        racer.setCountry(editedRacer.getCountry());
        racer.setAge(editedRacer.getAge());
        racer.setPoints(editedRacer.getPoints());
        return new RacerResponse(racerRepository.save(racer));
    }

    //Deletes a racer based on ID
    public void deleteRacer(int id){
        racerRepository.deleteById(id);
    }

}
