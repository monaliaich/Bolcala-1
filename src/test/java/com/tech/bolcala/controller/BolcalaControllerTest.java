package com.tech.bolcala.controller;

import com.tech.bolcala.model.Player;
import com.tech.bolcala.service.BolcalaService;
import com.tech.bolcala.service.BolcalaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class BolcalaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private BolcalaServiceImpl bolcalaServiceImpl;

    @InjectMocks
    private BolcalaController bolcalaController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(bolcalaController).build();

        Arrays.setAll(BolcalaService.p1Board, index -> (index < 6) ? 6 : 0);
        Arrays.setAll(BolcalaService.p2Board, index -> (index < 6) ? 6 : 0);
    }

    @Test
    public void testGetPitsDetails() throws Exception {
        List<int[]> list = new ArrayList<>();
        list.add(BolcalaService.p2Board);
        list.add(BolcalaService.p1Board);

        when(bolcalaServiceImpl.getPitsDetails(anyString()))
                .thenReturn(list);

        mockMvc.perform(get("/api/v1/bolcala/getPitsDetails/{player}","Player1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$[0][0]").value(6))
                .andExpect(jsonPath("$[0][6]").value(0))
                .andExpect(jsonPath("$[1][0]").value(6))
                .andExpect(jsonPath("$[1][6]").value(0));

        verify(bolcalaServiceImpl, times(1)).getPitsDetails(anyString());
    }

    @Test
    public void testmakeMove_PlayerOne() throws Exception {

        Player player = new Player();
        player.setSelectedPit(5);
        when(bolcalaServiceImpl.playGameByPlayerOne(any(Player.class)))
                .thenReturn(player);

        mockMvc.perform(post("/api/v1/bolcala/playerMove")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"selectedPit\": 5}")
                        .param("playerName", "PlayerOne"))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.selectedPit").value(5));

        verify(bolcalaServiceImpl, times(1)).playGameByPlayerOne(player);
    }

    @Test
    public void testResetGame() throws Exception {

        String expectedResponse = "Restart the Game from beginning !!";
        when(bolcalaServiceImpl.resetGame()).thenReturn(expectedResponse);

        mockMvc.perform(post("/api/v1/bolcala/resetGame"))
                .andExpect(status().isAccepted())
                .andExpect(content().string(expectedResponse));
    }
}
