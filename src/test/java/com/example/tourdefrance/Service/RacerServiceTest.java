package com.example.tourdefrance.Service;

import com.example.tourdefrance.Entity.Racer;
import com.example.tourdefrance.Entity.Team;
import com.example.tourdefrance.Repository.RacerRepository;
import com.example.tourdefrance.Repository.TeamRepository;
import com.example.tourdefrance.dto.RacerRequest;
import com.example.tourdefrance.dto.RacerResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class RacerServiceTest {

    @Autowired
    RacerRepository racerRepository;

    RacerService racerService;

    @Autowired
    TeamRepository teamRepository;


    static int racer1Id, racer2Id, team1Id;
    static String team1Name, team2Name;

    @BeforeEach
    void setup(@Autowired RacerRepository racerRepository, @Autowired TeamRepository teamRepository){
        racerRepository.deleteAll();
        teamRepository.deleteAll();

        Team team1 = teamRepository.save(new Team("Premier Tech"));
        Team team2 = teamRepository.save(new Team("Visma"));

        Racer racer1 = racerRepository.save(new Racer("Mads", "Würtz Schmidt", "Denmark", 28, 2));
        Racer racer2 = racerRepository.save(new Racer("Dennis", "Knudsen", "Denmark", 34, 1));

        racer1Id = racer1.getId();
        racer2Id = racer2.getId();
        team1Name = team1.getName();
        team2Name = team2.getName();
        team1Id = team1.getId();

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

        List<RacerResponse> racers = racerService.getAllRacers("Visma");
        assertEquals(1, racers.size());
        }

    @Test
    void getRacer() throws Exception{
        RacerResponse racer = racerService.getRacer(racer1Id);
        String racerName = "Mads";
        assertEquals(racerName, racer.getFirstName());
    }

    @Test
    void addRacer() throws Exception{
        Racer newRacer = new Racer("Phil", "Colin", "UK", 24, 3);
        RacerResponse addedRacer = racerService.addRacer(new RacerRequest(
                newRacer.getFirstName(),
                newRacer.getLastName(),
                newRacer.getCountry(),
                newRacer.getAge(),
                newRacer.getPoints()),
                team1Id);
        //When testing all tests, it creates two new entities (racers),
        //this means it has created 4 racers before it runs the addRacer method and therefore the new entry will be given the id "5".
        assertEquals(5, addedRacer.getId());
        assertEquals("Phil",addedRacer.getFirstName());
    }

    @Test
    void editRacer() throws Exception {

        Racer racerToEdit = new Racer("Mads", "Würtz Schmidt", "Finland", 30, 4);
        racerService.editRacer(new RacerRequest(
                racerToEdit.getFirstName(),
                racerToEdit.getLastName(),
                racerToEdit.getCountry(),
                racerToEdit.getAge(),
                racerToEdit.getPoints()
                ), racer1Id);
        RacerResponse editedRacer = racerService.getRacer(racer1Id);
        assertEquals(editedRacer.getCountry(), racerToEdit.getCountry());
        assertEquals(editedRacer.getPoints(), racerToEdit.getPoints());
    }


    @Test
    void deleteRacer() {
        List<RacerResponse>  originalSize = racerService.getAllRacers("Premier Tech");
        assertEquals(1,originalSize.size());

        racerRepository.deleteById(racer1Id);

        List<RacerResponse>  newSize = racerService.getAllRacers("Premier Tech");
        assertEquals(0,newSize.size());

    }

}