package com.github.lothar.katas.tennis;

import static com.github.lothar.katas.tennis.Score.FOURTY;

import java.util.Collection;

public class Advantage extends AbstractScoreCalculator {
    private Player playerWithAdvantage;

    public Advantage(Collection<Player> players, Player playerWithAdvantage) {
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
