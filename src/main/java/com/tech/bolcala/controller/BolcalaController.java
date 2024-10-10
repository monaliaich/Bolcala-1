package com.tech.bolcala.controller;

import com.tech.bolcala.model.Player;
import com.tech.bolcala.service.BolcalaServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bolcala")
public class BolcalaController {

    @Autowired
    private BolcalaServiceImpl bolcalaService;

    @GetMapping("/getPitsDetails/{player}")
    public ResponseEntity<List<int[]>> getPitsDetails(@PathVariable String player) {
        return new ResponseEntity<>(bolcalaService.getPitsDetails(player), HttpStatus.ACCEPTED);
    }

    @PostMapping("/playerMove")
    public ResponseEntity<Player> makeMove(@Valid @RequestBody Player player,
                                           @RequestParam String playerName) {
        if(playerName.equals("PlayerOne")) {
            return new ResponseEntity<>(bolcalaService.playGameByPlayerOne(player), HttpStatus.ACCEPTED);
        }else{
            return new ResponseEntity<>(bolcalaService.playGameByPlayerTwo(player), HttpStatus.ACCEPTED);
        }
    }

    @PostMapping("/resetGame")
    public ResponseEntity<String> resetGame() {
        return new ResponseEntity<>(bolcalaService.resetGame(),HttpStatus.ACCEPTED);
    }

}
