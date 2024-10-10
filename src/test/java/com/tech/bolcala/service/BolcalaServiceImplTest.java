package com.tech.bolcala.service;

import com.tech.bolcala.model.Player;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class BolcalaServiceImplTest {

    @InjectMocks
    private BolcalaServiceImpl bolcalaServiceImpl;

    @Mock
    PlayerOneServiceImpl playerOneServiceImpl;

    private Player playerOne;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        playerOne = new Player();
    }

    @Test
    public void testGetPitsDetails_Player1() {

        List<int[]> result = bolcalaServiceImpl.getPitsDetails("Player1");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertArrayEquals(BolcalaService.p1Board, result.get(0));
    }

    @Test
    public void testGetPitsDetails_Player2() {

        List<int[]> result = bolcalaServiceImpl.getPitsDetails("Player2");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertArrayEquals(BolcalaService.p2Board, result.get(0));
    }

    @Test
    public void testResetGame() {

        String result = bolcalaServiceImpl.resetGame();
        assertEquals("Restart the Game from beginning !!", result);
    }

    @Test
    public void testPlayGameByPlayerOne_WhenGameOver() {

        playerOne.setBoardSetUp(BolcalaService.p1Board);
        when(playerOneServiceImpl.gameOver(any(Player.class)))
                .thenReturn(true);

        Player result = bolcalaServiceImpl.playGameByPlayerOne(playerOne);
        assertEquals(playerOne, result);
        assertEquals("Gave is Over !!", playerOne.getMessage());
        verify(playerOneServiceImpl, times(1)).gameOver(playerOne);
        verify(playerOneServiceImpl, never()).playRound(playerOne);
    }

    @Test
    public void testPlayGameByPlayerOne_WhenGameNotOver() {

        playerOne.setBoardSetUp(BolcalaService.p1Board);

        when(playerOneServiceImpl.gameOver(playerOne)).thenReturn(false);
        when(playerOneServiceImpl.playRound(playerOne)).thenReturn(playerOne);

        Player result = bolcalaServiceImpl.playGameByPlayerOne(playerOne);

        assertEquals(playerOne, result);
        assertEquals(playerOne.getBoardSetUp()[BolcalaService.p1Board.length - 1], playerOne.getLargePit());
        verify(playerOneServiceImpl, times(1)).gameOver(playerOne);
        verify(playerOneServiceImpl, times(1)).playRound(playerOne);
    }

}
