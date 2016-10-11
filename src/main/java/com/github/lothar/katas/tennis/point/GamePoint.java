package com.github.lothar.katas.tennis.point;

import com.github.lothar.katas.tennis.Player;
import com.github.lothar.katas.tennis.Players;

public class GamePoint extends AbstractPoint {

    public GamePoint(Players players) {
        super(players);
    }

    public void wonBy(Player player) {
        player.incrementGamesWon();
        if (players.areInTieBreak()) {
            players.setupTieBreak();
        } else {
            players.setupNewGame();
        }
    }
}
