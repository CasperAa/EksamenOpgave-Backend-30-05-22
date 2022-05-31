package com.example.tourdefrance.api;

import com.example.tourdefrance.Service.RaceService;
import com.example.tourdefrance.dto.RaceDataResponse;
import com.example.tourdefrance.dto.RaceResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RequestMapping("/api/races")
public class RaceController {

    RaceService raceService;

    public RaceController(RaceService raceService){
        this.raceService = raceService;
    }

    //Get all Races
    @GetMapping
    public List<RaceResponse> getAllRaces() {
        return raceService.getAllRaces();
    }

    @GetMapping("/race/{id}")
    public List<RaceDataResponse> getRaceWithRaceId(@PathVariable int id) {
        return raceService.getRaceDataByRaceID(id);
    }

    @GetMapping("/cyclist/{id}")
    public List<RaceDataResponse> getRaceWithCyclistId(@PathVariable int id) {
        return raceService.getRaceDataByCyclistId(id);
    }

}
