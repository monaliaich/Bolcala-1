package com.tech.bolcala.service;

import com.tech.bolcala.model.*;

public interface PlayerService {

    boolean gameOver(Player player);
    Player playRound(Player player);
    boolean dropStones(Player playerOne);
}
