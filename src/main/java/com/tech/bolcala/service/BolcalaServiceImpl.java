package com.tech.bolcala.service;

import com.tech.bolcala.exception.InvalidInputException;
import com.tech.bolcala.model.Player;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Getter
@Service
public class BolcalaServiceImpl implements BolcalaService{

    @Autowired
    private PlayerTwoServiceImpl playerTwoServiceImpl;

    @Autowired
    private PlayerOneServiceImpl playerOneServiceImpl;

    public BolcalaServiceImpl() {
        BolcalaService.fillBoard();
    }

    @Override
    public List<int[]> getPitsDetails(String player) {

        return switch (player) {
            case "Player1" -> List.of(BolcalaService.p1Board);
            case "Player2" -> List.of(BolcalaService.p2Board);
            default -> throw new InvalidInputException("Player not found with id: " + player,601, LocalDateTime.now());
        };
    }

    @Override
    public String resetGame() {
        BolcalaService.fillBoard();
        return "Restart the Game from beginning !!";
    }

    @Override
    public Player playGameByPlayerOne(Player playerOne) {

        playerOne.setBoardSetUp(BolcalaService.p1Board);
        if(!playerOneServiceImpl.gameOver(playerOne)){
            playerOne = playerOneServiceImpl.playRound(playerOne);
            playerOne.setLargePit(playerOne.getBoardSetUp()[BolcalaService.p1Board.length-1]);
            }
        else
            playerOne.setMessage("Gave is Over !!");
        return playerOne;
    }

    @Override
    public Player playGameByPlayerTwo(Player playerTwo) {

        playerTwo.setBoardSetUp(BolcalaService.p2Board);
        if(!playerTwoServiceImpl.gameOver(playerTwo)){
            playerTwo = playerTwoServiceImpl.playRound(playerTwo);
            playerTwo.setLargePit(playerTwo.getBoardSetUp()[BolcalaService.p2Board.length-1]);
        }
        else
            playerTwo.setMessage("Gave is Over !!");
        return playerTwo;
    }


}
