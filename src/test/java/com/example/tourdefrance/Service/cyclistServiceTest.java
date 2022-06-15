package com.example.tourdefrance.Service;

import com.example.tourdefrance.Entity.Cyclist;
import com.example.tourdefrance.Entity.Team;
import com.example.tourdefrance.Repository.CyclistRepository;
import com.example.tourdefrance.Repository.RaceDataRepository;
import com.example.tourdefrance.Repository.TeamRepository;
import com.example.tourdefrance.dto.CyclistRequest;
import com.example.tourdefrance.dto.CyclistResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class cyclistServiceTest {

    @Autowired
    CyclistRepository cyclistRepository;

    @Autowired
    CyclistService cyclistService;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    RaceDataRepository raceDataRepository;


    static int cyclist1Id, cyclist2Id, team1Id;
    static String team1Name, team2Name;

    @BeforeAll
    static void setup(@Autowired CyclistRepository cyclistRepository, @Autowired TeamRepository teamRepository){
        cyclistRepository.deleteAll();
        teamRepository.deleteAll();

        Team team1 = teamRepository.save(new Team("Premier Tech"));
        Team team2 = teamRepository.save(new Team("Visma"));

        Cyclist cyclist1 = cyclistRepository.save(new Cyclist("Mads", "Würtz Schmidt", "Denmark", 28, 2));
        Cyclist cyclist2 = cyclistRepository.save(new Cyclist("Dennis", "Knudsen", "Denmark", 34, 1));

        cyclist1Id = cyclist1.getId();
        cyclist2Id = cyclist2.getId();
        team1Name = team1.getName();
        team2Name = team2.getName();
        team1Id = team1.getId();

        team1.addCyclist(cyclist1);
        team2.addCyclist(cyclist2);

        teamRepository.saveAll(List.of(team1, team2));
    }

    @BeforeEach
    void setupService(){
        cyclistService = new CyclistService(cyclistRepository, teamRepository, raceDataRepository);
    }

    /*
    @Test
    void getAllCyclist() {
        List<CyclistResponse> cyclists = cyclistService.getAllCyclists("Visma");
        assertEquals(1, cyclists.size());
        }

    @Test
    void getCyclist() throws Exception{
        CyclistResponse cyclist = cyclistService.getCyclist(cyclist1Id);
        String cyclistName = "Mads";
        assertEquals(cyclistName, cyclist.getFirstName());
    }

    @Test
    void addCyclist() throws Exception{
        Cyclist newCyclist = new Cyclist("Phil", "Colin", "UK", 24, 3);
        CyclistResponse addCyclist = cyclistService.addCyclist(new CyclistRequest(
                newCyclist.getFirstName(),
                newCyclist.getLastName(),
                newCyclist.getCountry(),
                newCyclist.getAge(),
                newCyclist.getPoints()),
                team1Id);
        //When testing all tests, it creates two new entities (cyclists),
        //this means it has created 4 cyclists before it runs the addCyclist method and therefore the new entry will be given the id "5".
        assertEquals(4, addCyclist.getId());
        assertEquals("Phil",addCyclist.getFirstName());
    }

    @Test
    void editCyclist() throws Exception {
        Cyclist cyclistToEdit = new Cyclist("Mads", "Würtz Schmidt", "Finland", 30, 4);
        cyclistService.editCyclist(new CyclistRequest(
                cyclistToEdit.getFirstName(),
                cyclistToEdit.getLastName(),
                cyclistToEdit.getCountry(),
                cyclistToEdit.getAge(),
                cyclistToEdit.getPoints()
                ), cyclist1Id);
        CyclistResponse editedCyclist = cyclistService.getCyclist(cyclist1Id);
        assertEquals(editedCyclist.getCountry(), cyclistToEdit.getCountry());
        assertEquals(editedCyclist.getPoints(), cyclistToEdit.getPoints());
    }


    @Test
    void deleteCyclist() {
        List<CyclistResponse>  originalSize = cyclistService.getAllCyclists("Premier Tech");
        assertEquals(1,originalSize.size());
        cyclistRepository.deleteById(cyclist1Id);
        List<CyclistResponse>  newSize = cyclistService.getAllCyclists("Premier Tech");
        assertEquals(0,newSize.size());

    }
 */

}