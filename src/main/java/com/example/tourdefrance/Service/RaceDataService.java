package com.example.tourdefrance.Service;

import com.example.tourdefrance.Entity.RaceData;
import com.example.tourdefrance.Repository.RaceDataRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class RaceDataService {

    RaceDataRepository raceDataRepository;

    //Deletes a race based on ID
    public void deleteRaceData(int id){
        List<RaceData> toBeDeleted = raceDataRepository.findRaceDataByCyclist_Id(id);
        for(RaceData race: toBeDeleted){
            raceDataRepository.delete(race);
        }
    }
}
