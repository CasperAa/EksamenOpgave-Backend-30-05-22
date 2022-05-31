package com.example.tourdefrance.Repository;
import com.example.tourdefrance.Entity.RaceData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RaceDataRepository extends JpaRepository <RaceData, Integer>{

    List<RaceData> findRaceDataByRace_Id(int id);
    List<RaceData> findRaceDataByCyclist_Id(int id);
}
