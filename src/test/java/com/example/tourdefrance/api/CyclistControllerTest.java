package com.example.tourdefrance.api;

import com.example.tourdefrance.Entity.Team;
import com.example.tourdefrance.Repository.CyclistRepository;
import com.example.tourdefrance.Repository.TeamRepository;
import com.example.tourdefrance.Service.CyclistService;
import com.example.tourdefrance.dto.CyclistRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class CyclistControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    CyclistController cyclistController;

    @Autowired
    CyclistRepository cyclistRepository;

    @Autowired
    CyclistService cyclistService;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    ObjectMapper objectMapper;

    Team team1 = new Team("Visma");
    int team1Id = team1.getId();


    @BeforeEach
    public void setup() {
        cyclistRepository.deleteAll();
        teamRepository.deleteAll();

    }

    @Test
    void testAddRacer() throws Exception {
        CyclistRequest newRacer = new CyclistRequest("Phil", "Colin", "UK", 24, 3);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/cyclists"+team1Id)
                        .contentType("application/json")
                        .accept("application/json")
                        .content(objectMapper.writeValueAsString(newRacer)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
        // Verify that it actually ended in the database
        assertEquals(1, cyclistRepository.count());
    }

}