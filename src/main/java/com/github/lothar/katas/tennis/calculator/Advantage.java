package com.github.lothar.katas.tennis.calculator;

import static com.github.lothar.katas.tennis.score.NormalScore.FOURTY;

import com.github.lothar.katas.tennis.Player;
import com.github.lothar.katas.tennis.Players;

public class Advantage extends AbstractScoreCalculator {
    private Player playerWithAdvantage;

    public Advantage(Players players, Player playerWithAdvantage) {
        super(players);
        this.playerWithAdvantage = playerWithAdvantage;
    }

    @Override
    public void playerScores(Player player) {
        if (playerWithAdvantage.equals(player)) {
            winsGame(player);
        } else {
            players.stream() //
                    .forEach(p -> p.setScore(FOURTY));
        }
    }
}
