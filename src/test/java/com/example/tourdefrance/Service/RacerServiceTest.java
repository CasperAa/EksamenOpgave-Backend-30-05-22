package com.example.tourdefrance.Service;

import com.example.tourdefrance.Entity.Racer;
import com.example.tourdefrance.Entity.Team;
import com.example.tourdefrance.Repository.RacerRepository;
import com.example.tourdefrance.Repository.TeamRepository;
import com.example.tourdefrance.dto.RacerResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.hamcrest.MatcherAssert.assertThat;


@DataJpaTest
class RacerServiceTest {

    @Autowired
    RacerRepository racerRepository;

    RacerService racerService;

    @Autowired
    TeamRepository teamRepository;


    static int racer1Id, racer2Id;
    static String team1Name, team2Name;

    @BeforeAll
    static void setup(@Autowired RacerRepository racerRepository, @Autowired TeamRepository teamRepository){
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

    @BeforeEach
    void setupService(){
        racerService = new RacerService(racerRepository, teamRepository);
    }

    @Test
    void getAllRacers() {
        List<RacerResponse> racerResponses = racerService.getAllRacers(null);
        assertEquals(2, racerResponses.size());
        }

    @Test
    void getRacer() throws Exception{
        RacerResponse racer = racerService.getRacer(racer1Id);
        String racerName = "Dennis";
        assertEquals(racerName, racer.getFirstName());
    }

    @Test
    void addRacer() {
    }

    @Test
    void editRacer() {
    }

    @Test
    void editRacerPoints() {
    }

    @Test
    void deleteRacer() {
    }

}