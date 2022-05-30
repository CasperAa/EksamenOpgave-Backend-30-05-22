package com.example.tourdefrance.Repository;

import com.example.tourdefrance.Entity.Racer;
import com.example.tourdefrance.Entity.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
class RacerRepositoryTest {

    @Autowired
    RacerRepository racerRepository;

    @Autowired
    TeamRepository teamRepository;

    //Store attributes for the test methods
    int racer1Id, racer2Id;
    String team1Name, team2Name;


    @BeforeEach
    void setUp() {
        racerRepository.deleteAll();
        teamRepository.deleteAll();
        Team team1 = new Team("Premier Tech");
        Team team2 = new Team("Visma");

        Racer racer1 = racerRepository.save(new Racer("Mads", "Würtz Schmidt", "Denmark", 28, 2));
        Racer racer2 = racerRepository.save(new Racer("Dennis", "Knudsen", "Denmark", 34, 1));

        racer1Id = racer1.getId();
        racer2Id = racer2.getId();
        team1Name = team1.getName();
        team2Name = team2.getName();

        team1.addRacers(Set.of(racer1));
        team2.addRacers(Set.of(racer2));

        teamRepository.saveAll(List.of(team1, team2));
    }

    @Test
    public void findRacerByTeam_Name() {
        List<Racer> allRacersInTeam = racerRepository.findRacerByTeam_Name(team1Name);
        assertEquals(1,allRacersInTeam.size());
    }

}