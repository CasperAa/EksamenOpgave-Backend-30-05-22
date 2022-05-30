package com.example.tourdefrance.api;

import com.example.tourdefrance.Entity.Racer;
import com.example.tourdefrance.Entity.Team;
import com.example.tourdefrance.Repository.RacerRepository;
import com.example.tourdefrance.Repository.TeamRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class RacerControllerTest {

    @Autowired
    RacerController racerController;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    static int racer1, racer2, racer3, racer4;

    @BeforeEach
    public void setup() {
        Team team1 = new Team("Premier Tech");
        Team team2 = new Team("Visma");

        racer1 = new Racer("Mads", "Würtz Schmidt", "Denmark", 28, 2).getId();
        racer2 = new Racer("Dennis", "Knudsen", "Denmark", 34, 1).getId();
        racer3 = new Racer("Martin", "Finnerrup", "Denmark", 23, 2).getId();
        racer4 = new Racer("Victor", "Fuglsang", "Denmark", 31, 1).getId();
    }

    @Test
    void getAllRacers() throws Exception {
        String title = "$[?(@.racers == '%s')]";
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/racers")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath(title, "Mads").exists())
                .andExpect(MockMvcResultMatchers.jsonPath(title, "Dennis").exists())
                .andExpect(MockMvcResultMatchers.content().string(containsString("Mads")))
                .andExpect(MockMvcResultMatchers.content().string(containsString("Dennis")));
    }

    @Test
    void getRacer() {
    }

    @Test
    void addRacer() {
    }

    @Test
    void editRacer() {
    }

    @Test
    void editPoints() {
    }

    @Test
    void deleteRacer() {
    }
}