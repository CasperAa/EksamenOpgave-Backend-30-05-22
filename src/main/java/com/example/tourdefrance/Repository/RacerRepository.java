package com.example.tourdefrance.Repository;

import com.example.tourdefrance.Entity.Racer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RacerRepository extends JpaRepository <Racer, Integer>{

    List<Racer> findRacerByTeam_Name(String name);
    List<Racer> findRacerByCountry(String country);

}
