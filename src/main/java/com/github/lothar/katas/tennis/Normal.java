package com.github.lothar.katas.tennis;

import static com.github.lothar.katas.tennis.score.NormalScore.FOURTY;

public class Normal extends AbstractScoreCalculator {

    public Normal(Players players) {
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
