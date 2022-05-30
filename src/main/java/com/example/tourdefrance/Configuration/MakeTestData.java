package com.example.tourdefrance.Configuration;

import com.example.tourdefrance.Entity.Racer;
import com.example.tourdefrance.Entity.Team;
import com.example.tourdefrance.Repository.RacerRepository;
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

    RacerRepository racerRepository;
    TeamRepository teamRepository;

    public MakeTestData(RacerRepository racerRepository, TeamRepository teamRepository) {
        this.racerRepository = racerRepository;
        this.teamRepository = teamRepository;
    }

    public void makeRacersAndTeams(){
        Team team1 = new Team("Premier Tech");
        Team team2 = new Team("Visma");
        Team team3 = new Team("Lotto Soudal");

        Racer racer1 = new Racer("Mads", "Würtz Schmidt", "Denmark", 28, 2);
        Racer racer2 = new Racer("Omer", "Goldstein", "Israel", 25, 1);
        Racer racer3 = new Racer("Michael", "Woods", "Canada", 35, 0);
        Racer racer4 = new Racer("Brent", "Van Moer", "Belgium", 24, 3);
        Racer racer5 = new Racer("Tim", "Wellens", "Belgium", 31, 1);
        Racer racer6 = new Racer("Philippe", "Gilbert", "Belgium", 39, 0);
        Racer racer7 = new Racer("Jonas", "Vingegaard", "Denmark", 25, 3);
        Racer racer8 = new Racer("Rohan", "Dennis", "Australia", 32, 0);
        Racer racer9 = new Racer("Sepp", "Kuss", "USA", 27, 2);

        team1.addRacers(Set.of(racer1, racer2, racer3));
        team2.addRacers(Set.of(racer4,racer5, racer6));
        team3.addRacers(Set.of(racer7, racer8, racer9));

        teamRepository.saveAll(List.of(team1, team2, team3));
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        makeRacersAndTeams();

    }

}
