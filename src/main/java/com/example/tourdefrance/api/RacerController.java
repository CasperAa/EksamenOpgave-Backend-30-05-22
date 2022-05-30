package com.example.tourdefrance.api;

import com.example.tourdefrance.Entity.Team;
import com.example.tourdefrance.Service.RacerService;
import com.example.tourdefrance.dto.RacerRequest;
import com.example.tourdefrance.dto.RacerResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RequestMapping("/api/racers")
public class RacerController {

    RacerService racerService;

    public RacerController (RacerService racerService){
        this.racerService = racerService;
    }

    //Get all racers or all racers based on team
    @GetMapping
    public List<RacerResponse> getAllRacers(@RequestParam(value = "input", required = false) String input) {
        return racerService.getAllRacers(input);
    }

    //Get a specific racer based on ID
    @GetMapping("/{id}")
    public RacerResponse getRacer(@PathVariable int id) throws Exception {
        return racerService.getRacer(id);
    }

    //Adds a new racer to DB given that the team ID is known
    @PostMapping("/{id}")
    public RacerResponse addRacer(@RequestBody RacerRequest body, @PathVariable int id) throws Exception{
        return racerService.addRacer(body, id);
    }

    //Edit a Racer  (all attributes)
    @PutMapping("/{id}")
    public RacerResponse editRacer(@RequestBody RacerRequest body, @PathVariable int id){
        return racerService.editRacer(body,id);
    }

    //Delete a racer from DB
    @DeleteMapping("/{id}")
    public void deleteRacer(@PathVariable int id){
        racerService.deleteRacer(id);
    }

}
