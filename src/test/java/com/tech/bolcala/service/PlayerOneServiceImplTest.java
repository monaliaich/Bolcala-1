package com.tech.bolcala.service;

import com.tech.bolcala.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class PlayerOneServiceImplTest {

    @InjectMocks
    private PlayerOneServiceImpl playerOneServiceImpl;

    private Player playerOne;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        playerOne = new Player();
    }

    @Test
    public void testGameOver_AllZeros() {

        int[] boardSetup = {0, 0, 0, 0, 0, 0, 0}; // All pits are empty
        playerOne.setBoardSetUp(boardSetup);
        boolean result = playerOneServiceImpl.gameOver(playerOne);
        assertTrue(result);
    }

    @Test
    public void testGameOver_NonZeros() {

        int[] boardSetup = {0, 0, 0, 1, 0, 0, 0}; // All pits are empty
        playerOne.setBoardSetUp(boardSetup);
        boolean result = playerOneServiceImpl.gameOver(playerOne);
        assertFalse(result);
    }

    @Test
    public void testPlayRound_EndInBigPit() {

        playerOne.setSelectedPit(5);
        playerOne.setBoardSetUp(new int[]{0, 0, 0, 0, 0, 3, 0});

        when(playerOneServiceImpl.dropStones(playerOne)).thenReturn(true);
        Player result = playerOneServiceImpl.playRound(playerOne);

        assertEquals("Player 1 turn", result.getMessage());
        assertTrue(result.isPlayerTurn());
        assertEquals("Player1", result.getNextPlayerName());
    }

    @Test
    public void testPlayRound_NotEndInBigPit() {

        playerOne.setSelectedPit(5);
        playerOne.setBoardSetUp(new int[]{0, 0, 0, 0, 0, 3, 0});

        when(playerOneServiceImpl.dropStones(playerOne)).thenReturn(false);
        Player result = playerOneServiceImpl.playRound(playerOne);

        assertEquals("Player 2 turn", result.getMessage());
        assertFalse(result.isPlayerTurn());
        assertEquals("Player2", result.getNextPlayerName());
    }
}
