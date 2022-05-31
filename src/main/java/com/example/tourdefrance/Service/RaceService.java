package com.example.tourdefrance.Service;

import com.example.tourdefrance.Entity.Race;
import com.example.tourdefrance.Entity.RaceData;
import com.example.tourdefrance.Entity.Team;
import com.example.tourdefrance.Error.Client4xxException;
import com.example.tourdefrance.Repository.CyclistRepository;
import com.example.tourdefrance.Repository.RaceDataRepository;
import com.example.tourdefrance.Repository.RaceRepository;
import com.example.tourdefrance.Repository.TeamRepository;
import com.example.tourdefrance.dto.CyclistResponse;
import com.example.tourdefrance.dto.RaceDataResponse;
import com.example.tourdefrance.dto.RaceResponse;
import com.example.tourdefrance.dto.TeamResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RaceService {

    RaceDataRepository raceDataRepository;
    RaceRepository raceRepository;

    public RaceService(RaceRepository raceRepository, RaceDataRepository raceDataRepository) {
        this.raceRepository = raceRepository;
        this.raceDataRepository = raceDataRepository;
    }

    //Returns all Races with
    public List<RaceResponse> getAllRaces() {
        List<Race> races = raceRepository.findAll();
        return races.stream().map((data) -> new RaceResponse(data)).collect(Collectors.toList());
    }

    //Returns all Races with
    public RaceResponse getRace(int id) throws Exception{
        Race race = raceRepository.findById(id).orElseThrow(()->new Client4xxException("No cyclist with that id exists"));
        return new RaceResponse(race);
    }

    //Returns a race's Data with Race ID
    public List<RaceDataResponse> getRaceDataByRaceID(int id) {
        List<RaceData> raceData = raceDataRepository.findRaceDataByRace_Id(id);
        return raceData.stream().map((data) -> new RaceDataResponse(data)).collect(Collectors.toList());
    }

    //Returns race data based on cyclist id
    public List<RaceDataResponse> getRaceDataByCyclistId(int id) {
        List<RaceData> raceData = raceDataRepository.findRaceDataByCyclist_Id(id);
        return raceData.stream().map((data) -> new RaceDataResponse(data)).collect(Collectors.toList());
    }



}
