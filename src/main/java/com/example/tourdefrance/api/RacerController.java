package com.example.tourdefrance.api;

import com.example.tourdefrance.Entity.Team;
import com.example.tourdefrance.Service.RacerService;
import com.example.tourdefrance.dto.RacerRequest;
import com.example.tourdefrance.dto.RacerResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/racers")
public class RacerController {

    RacerService racerService;

    public RacerController (RacerService racerService){
        this.racerService = racerService;
    }

    //Get all racers or all racers based on team
    @GetMapping
    public List<RacerResponse> getAllRacers(@RequestParam(value = "team", required = false) String teamName) {
        return racerService.getAllRacers(teamName);
    }

    //Get a specific racer based on ID
    @GetMapping("/{id}")
    public RacerResponse getRacer(@PathVariable int id) throws Exception {
        return racerService.getRacer(id);
    }

    //Adds a new racer to DB
    @PostMapping("/{id}")
    public RacerResponse addRacer(@RequestBody RacerRequest body, @PathVariable int id) throws Exception{
        return racerService.addRacer(body, id);
    }

    //Edit a Racer  (all attributes
    @PutMapping("/{id}")
    public RacerResponse editRacer(@RequestBody RacerRequest body, @PathVariable int id){
        return racerService.editRacer(body,id);
    }

    //Update a Racers Points
    @PatchMapping ("/{id}/{newPoints}")
    public void editPoints(@PathVariable int id,@PathVariable int newPoints) throws Exception {
        racerService.editRacerPoints(id,newPoints);
    }

    //Delete a racer from DB
    @DeleteMapping("/{id}")
    public void deleteRacer(@PathVariable int id){
        racerService.deleteRacer(id);
    }

}
