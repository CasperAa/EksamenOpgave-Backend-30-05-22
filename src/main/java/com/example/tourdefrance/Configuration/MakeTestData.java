package com.example.tourdefrance.Configuration;

import com.example.tourdefrance.Entity.Cyclist;
import com.example.tourdefrance.Entity.Race;
import com.example.tourdefrance.Entity.RaceData;
import com.example.tourdefrance.Entity.Team;
import com.example.tourdefrance.Repository.CyclistRepository;
import com.example.tourdefrance.Repository.RaceDataRepository;
import com.example.tourdefrance.Repository.RaceRepository;
import com.example.tourdefrance.Repository.TeamRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@Profile("!test")
public class MakeTestData implements ApplicationRunner {

    CyclistRepository cyclistRepository;
    TeamRepository teamRepository;
    RaceRepository raceRepository;
    RaceDataRepository raceDataRepository;

    public MakeTestData(CyclistRepository cyclistRepository, TeamRepository teamRepository, RaceRepository raceRepository, RaceDataRepository raceDataRepository) {
        this.cyclistRepository = cyclistRepository;
        this.teamRepository = teamRepository;
        this.raceRepository = raceRepository;
        this.raceDataRepository = raceDataRepository;

    }

    public void makeRacersAndTeams(){
        Race race1 = new Race("First Race", "Flat", 183);
        Race race2 = new Race("Second Race", "Mountain", 164);
        Race race3 = new Race("Third Race", "Sprint", 16);

        Team team1 = new Team("Premier Tech");
        Team team2 = new Team("Visma");
        Team team3 = new Team("Lotto Soudal");

        Cyclist cyclist1 = new Cyclist("Mads", "Würtz Schmidt", "Denmark", 28, 2);
        Cyclist cyclist2 = new Cyclist("Omer", "Goldstein", "Israel", 25, 1);
        Cyclist cyclist3 = new Cyclist("Michael", "Woods", "Canada", 35, 0);
        Cyclist cyclist4 = new Cyclist("Brent", "Van Moer", "Belgium", 24, 3);
        Cyclist cyclist5 = new Cyclist("Tim", "Wellens", "Belgium", 31, 1);
        Cyclist cyclist6 = new Cyclist("Philippe", "Gilbert", "Belgium", 39, 0);
        Cyclist cyclist7 = new Cyclist("Jonas", "Vingegaard", "Denmark", 25, 3);
        Cyclist cyclist8 = new Cyclist("Rohan", "Dennis", "Australia", 32, 0);
        Cyclist cyclist9 = new Cyclist("Sepp", "Kuss", "USA", 27, 2);


        team1.addCyclists(Set.of(cyclist1, cyclist2, cyclist3));
        team2.addCyclists(Set.of(cyclist4, cyclist5, cyclist6));
        team3.addCyclists(Set.of(cyclist7, cyclist8, cyclist9));

        raceRepository.saveAll(List.of(race1, race2, race3));
        teamRepository.saveAll(List.of(team1, team2, team3));
        cyclistRepository.saveAll(List.of(cyclist1, cyclist2, cyclist3, cyclist4, cyclist5, cyclist6, cyclist7, cyclist8, cyclist9));

        raceDataRepository.save(new RaceData("05:21:32", 5, 1, cyclist1, race1));
        raceDataRepository.save(new RaceData("06:04:15", 2, 4, cyclist1, race2));
        raceDataRepository.save(new RaceData("00:32:56", 8, 0, cyclist1, race3));

        raceDataRepository.save(new RaceData("05:14:31", 1, 5, cyclist2, race1));
        raceDataRepository.save(new RaceData("06:12:19", 5, 1, cyclist2, race2));
        raceDataRepository.save(new RaceData("00:41:12", 9, 0, cyclist2, race3));

        raceDataRepository.save(new RaceData("05:17:32", 3, 3, cyclist3, race1));
        raceDataRepository.save(new RaceData("06:07:15", 6, 0, cyclist3, race2));
        raceDataRepository.save(new RaceData("00:26:56", 4, 2, cyclist3, race3));

        raceDataRepository.save(new RaceData("05:21:32", 5, 1, cyclist4, race1));
        raceDataRepository.save(new RaceData("06:04:15", 2, 4, cyclist4, race2));
        raceDataRepository.save(new RaceData("00:32:56", 8, 0, cyclist4, race3));

        raceDataRepository.save(new RaceData("05:21:32", 5, 1, cyclist5, race1));
        raceDataRepository.save(new RaceData("06:04:15", 2, 4, cyclist5, race2));
        raceDataRepository.save(new RaceData("00:32:56", 8, 0, cyclist5, race3));

        raceDataRepository.save(new RaceData("05:21:32", 5, 1, cyclist6, race1));
        raceDataRepository.save(new RaceData("06:04:15", 2, 4, cyclist6, race2));
        raceDataRepository.save(new RaceData("00:32:56", 8, 0, cyclist6, race3));

        raceDataRepository.save(new RaceData("05:21:32", 5, 1, cyclist7, race1));
        raceDataRepository.save(new RaceData("06:04:15", 2, 4, cyclist7, race2));
        raceDataRepository.save(new RaceData("00:32:56", 8, 0, cyclist7, race3));

        raceDataRepository.save(new RaceData("05:21:32", 5, 1, cyclist8, race1));
        raceDataRepository.save(new RaceData("06:04:15", 2, 4, cyclist8, race2));
        raceDataRepository.save(new RaceData("00:32:56", 8, 0, cyclist8, race3));

        raceDataRepository.save(new RaceData("05:21:32", 5, 1, cyclist9, race1));
        raceDataRepository.save(new RaceData("06:04:15", 2, 4, cyclist9, race2));
        raceDataRepository.save(new RaceData("00:32:56", 8, 0, cyclist9, race3));

    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        makeRacersAndTeams();

    }

}
