package com.example.tourdefrance.Repository;

import com.example.tourdefrance.Entity.Cyclist;
import com.example.tourdefrance.Entity.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
class CyclistRepositoryTest {

    @Autowired
    CyclistRepository cyclistRepository;

    @Autowired
    TeamRepository teamRepository;

    //Store attributes for the test methods
    int cyclist1Id, cyclist2Id;
    String team1Name, team2Name;


    @BeforeEach
    void setUp() {
        cyclistRepository.deleteAll();
        teamRepository.deleteAll();
        Team team1 = new Team("Premier Tech");
        Team team2 = new Team("Visma");

        Cyclist cyclist1 = cyclistRepository.save(new Cyclist("Mads", "Würtz Schmidt", "Denmark", 28, 2));
        Cyclist cyclist2 = cyclistRepository.save(new Cyclist("Dennis", "Knudsen", "Denmark", 34, 1));

        cyclist1Id = cyclist1.getId();
        cyclist2Id = cyclist2.getId();
        team1Name = team1.getName();
        team2Name = team2.getName();

        team1.addCyclists(Set.of(cyclist1));
        team2.addCyclists(Set.of(cyclist2));

        teamRepository.saveAll(List.of(team1, team2));
    }

    @Test
    public void findCyclistByTeamName() {
        List<Cyclist> allCyclistsInTeam = cyclistRepository.findCyclistByTeam_Name(team1Name);
        assertEquals(1,allCyclistsInTeam.size());
    }

}