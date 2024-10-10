package com.tech.bolcala.service;

import com.tech.bolcala.model.Player;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.function.Predicate;

@Component
public class PlayerTwoServiceImpl implements  PlayerService{

    @Override
    public boolean gameOver(Player player){
        int[] p2Board = BolcalaService.p2Board;
        return Arrays.stream(p2Board, 0, p2Board.length - 1)
                .anyMatch(value -> value == 0);
    }

    @Override
    public Player playRound(Player playerTwo) {

        Predicate<Boolean> isPlayerTwoTurn = playerTwoTurn -> dropStones(playerTwo);
        boolean endInBigPit = isPlayerTwoTurn.test(true); // Using true here since I can execute the method.
        if (endInBigPit) {
            playerTwo.setMessage("Player 2 turn");
            playerTwo.setPlayerTurn(true);
            playerTwo.setNextPlayerName("Player2");
        } else {
            playerTwo.setMessage("Player 1 turn");
            playerTwo.setPlayerTurn(false);
            playerTwo.setNextPlayerName("Player1");
        }

        return playerTwo;
    }

    @Override
    public boolean dropStones(Player playerTwo) {

        int pitIndex = playerTwo.getSelectedPit();
        int[] p1Board = BolcalaService.p1Board;
        int[] p2Board = BolcalaService.p2Board;

        int stones = p2Board[pitIndex];
        p2Board[pitIndex]= 0;
        int [] currArray = p2Board;
        int dropPit = pitIndex +1;
        int turnCheck = pitIndex +1;
        while(stones > 0){
            currArray[dropPit]+=1;
            stones -- ;
            dropPit ++;
            turnCheck ++;
            if(dropPit >= p2Board.length){
                currArray = p1Board;
                dropPit = 0;
            }
        }

        return turnCheck == p2Board.length && stones == 0;
    }
}
