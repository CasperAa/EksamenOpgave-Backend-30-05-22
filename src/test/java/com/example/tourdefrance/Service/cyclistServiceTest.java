package com.example.tourdefrance.Service;

import com.example.tourdefrance.Entity.Cyclist;
import com.example.tourdefrance.Entity.Team;
import com.example.tourdefrance.Repository.CyclistRepository;
import com.example.tourdefrance.Repository.TeamRepository;
import com.example.tourdefrance.dto.CyclistRequest;
import com.example.tourdefrance.dto.CyclistResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class cyclistServiceTest {

    @Autowired
    CyclistRepository cyclistRepository;

    CyclistService cyclistService;

    @Autowired
    TeamRepository teamRepository;


    static int racer1Id, racer2Id, team1Id;
    static String team1Name, team2Name;

    @BeforeEach
    void setup(@Autowired CyclistRepository cyclistRepository, @Autowired TeamRepository teamRepository){
        cyclistRepository.deleteAll();
        teamRepository.deleteAll();

        Team team1 = teamRepository.save(new Team("Premier Tech"));
        Team team2 = teamRepository.save(new Team("Visma"));

        Cyclist cyclist1 = cyclistRepository.save(new Cyclist("Mads", "Würtz Schmidt", "Denmark", 28, 2));
        Cyclist cyclist2 = cyclistRepository.save(new Cyclist("Dennis", "Knudsen", "Denmark", 34, 1));

        racer1Id = cyclist1.getId();
        racer2Id = cyclist2.getId();
        team1Name = team1.getName();
        team2Name = team2.getName();
        team1Id = team1.getId();

        team1.addRacers(Set.of(cyclist1));
        team2.addRacers(Set.of(cyclist2));

        teamRepository.saveAll(List.of(team1, team2));
    }

    @BeforeEach
    void setupService(){
        cyclistService = new CyclistService(cyclistRepository, teamRepository);
    }

    @Test
    void getAllRacers() {

        List<CyclistResponse> cyclists = cyclistService.getAllCyclists("Visma");
        assertEquals(1, cyclists.size());
        }

    @Test
    void getRacer() throws Exception{
        CyclistResponse racer = cyclistService.getCyclist(racer1Id);
        String racerName = "Mads";
        assertEquals(racerName, racer.getFirstName());
    }

    @Test
    void addRacer() throws Exception{
        Cyclist newCyclist = new Cyclist("Phil", "Colin", "UK", 24, 3);
        CyclistResponse addedRacer = cyclistService.addCyclist(new CyclistRequest(
                newCyclist.getFirstName(),
                newCyclist.getLastName(),
                newCyclist.getCountry(),
                newCyclist.getAge(),
                newCyclist.getPoints()),
                team1Id);
        //When testing all tests, it creates two new entities (cyclists),
        //this means it has created 4 cyclists before it runs the addRacer method and therefore the new entry will be given the id "5".
        assertEquals(5, addedRacer.getId());
        assertEquals("Phil",addedRacer.getFirstName());
    }

    @Test
    void editRacer() throws Exception {

        Cyclist cyclistToEdit = new Cyclist("Mads", "Würtz Schmidt", "Finland", 30, 4);
        cyclistService.editCyclist(new CyclistRequest(
                cyclistToEdit.getFirstName(),
                cyclistToEdit.getLastName(),
                cyclistToEdit.getCountry(),
                cyclistToEdit.getAge(),
                cyclistToEdit.getPoints()
                ), racer1Id);
        CyclistResponse editedRacer = cyclistService.getCyclist(racer1Id);
        assertEquals(editedRacer.getCountry(), cyclistToEdit.getCountry());
        assertEquals(editedRacer.getPoints(), cyclistToEdit.getPoints());
    }


    @Test
    void deleteRacer() {
        List<CyclistResponse>  originalSize = cyclistService.getAllCyclists("Premier Tech");
        assertEquals(1,originalSize.size());

        cyclistRepository.deleteById(racer1Id);

        List<CyclistResponse>  newSize = cyclistService.getAllCyclists("Premier Tech");
        assertEquals(0,newSize.size());

    }

}