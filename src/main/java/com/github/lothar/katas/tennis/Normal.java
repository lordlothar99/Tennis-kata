package com.github.lothar.katas.tennis;

import static com.github.lothar.katas.tennis.Score.FOURTY;

import java.util.Collection;

public class Normal extends AbstractScoreCalculator {

    public Normal(Collection<Player> players) {
        super(players);
    }

    @Override
    public void playerScores(Player player) {
        if (FOURTY.equals(player.getScore())) {
            winsGame(player);
        } else {
            player.incrementScore();
        }
    }
}
