package com.github.lothar.katas.tennis.point;

import com.github.lothar.katas.tennis.Player;
import com.github.lothar.katas.tennis.Players;

public class SetPoint extends AbstractPoint {

    public SetPoint(Players players) {
        super(players);
    }

    public void wonBy(Player player) {
        player.incrementGamesWon();
        player.incrementSetWon();
        players.setupNewSet();
    }

}
