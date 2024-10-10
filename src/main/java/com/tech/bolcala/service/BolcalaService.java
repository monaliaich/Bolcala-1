package com.tech.bolcala.service;

import java.util.Arrays;
import java.util.List;
import com.tech.bolcala.model.*;

public interface BolcalaService {

    static int[] p1Board = new int[7];
    static int[] p2Board = new int[7];

    static void fillBoard() {
        Arrays.setAll(BolcalaService.p1Board, index -> (index < 6) ? 6 : 0); // Initialize with 6 stones in 1st player pit
        Arrays.setAll(BolcalaService.p2Board, index -> (index < 6) ? 6 : 0); // Initialize with 6 stones in 2nd player pit
    }

    List<int[]> getPitsDetails(String player);

    String resetGame();

    Player playGameByPlayerOne(Player one);

    Player playGameByPlayerTwo(Player one);
}
