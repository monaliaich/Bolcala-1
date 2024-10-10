package com.tech.bolcala.service;

import com.tech.bolcala.model.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.function.Predicate;

@Component
public class PlayerOneServiceImpl implements PlayerService {

    @Override
    public boolean gameOver(Player playerOne) {
        return Arrays.stream(playerOne.getBoardSetUp(), 0, playerOne.getBoardSetUp().length - 1)
                .allMatch(value -> value == 0);
    }

    @Override
    public Player playRound(Player playerOne) {

        Predicate<Boolean> isPlayerOneTurn = playerOneTurn -> dropStones(playerOne);
        boolean endInBigPit = isPlayerOneTurn.test(true); // Using true here since I can execute the method.
        if (endInBigPit) {
            playerOne.setMessage("Player 1 turn");
            playerOne.setPlayerTurn(true);
            playerOne.setNextPlayerName("Player1");
        } else {
            playerOne.setMessage("Player 2 turn");
            playerOne.setPlayerTurn(false);
            playerOne.setNextPlayerName("Player2");
        }

        return playerOne;
    }
    @Override
    public boolean dropStones(Player playerOne){

        int pitIndex = playerOne.getSelectedPit();
        int[] p1Board = BolcalaService.p1Board;
        int[] p2Board = BolcalaService.p2Board;
        int stones = p1Board[pitIndex];
        p1Board[pitIndex]= 0;
        int [] currArray = p1Board;
        int dropPit = pitIndex +1;
        int turnCheck = pitIndex +1;
        while(stones > 0){
            currArray[dropPit]+=1;
            stones -- ;
            dropPit ++;
            turnCheck ++;
            if(dropPit >= p1Board.length){
                currArray = p2Board;
                dropPit = 0;
            }
        }
        return turnCheck == p1Board.length && stones == 0;

    }
}
