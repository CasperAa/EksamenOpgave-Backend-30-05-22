package com.example.tourdefrance.api;

import com.example.tourdefrance.Service.CyclistService;
import com.example.tourdefrance.dto.CyclistRequest;
import com.example.tourdefrance.dto.CyclistResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RequestMapping("/api/racers")
public class CyclistController {

    CyclistService cyclistService;

    public CyclistController(CyclistService cyclistService){
        this.cyclistService = cyclistService;
    }

    //Get all racers or all racers based on team
    @GetMapping
    public List<CyclistResponse> getAllCyclists(@RequestParam(value = "team", required = false) String team) {
        return cyclistService.getAllCyclists(team);
    }

    //Get a specific racer based on ID
    @GetMapping("/{id}")
    public CyclistResponse getCyclist(@PathVariable int id) throws Exception {
        return cyclistService.getCyclist(id);
    }

    //Adds a new racer to DB given that the team ID is known
    @PostMapping("/{id}")
    public CyclistResponse addCyclist(@RequestBody CyclistRequest body, @PathVariable int id) throws Exception{
        return cyclistService.addCyclist(body, id);
    }

    //Edit a Racer  (all attributes)
    @PutMapping("/{id}")
    public CyclistResponse editCyclist(@RequestBody CyclistRequest body, @PathVariable int id){
        return cyclistService.editCyclist(body,id);
    }

    //Delete a racer from DB
    @DeleteMapping("/{id}")
    public void deleteCyclist(@PathVariable int id){
        cyclistService.deleteCyclist(id);
    }

}
