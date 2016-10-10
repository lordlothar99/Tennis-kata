package com.github.lothar.katas.tennis.calculator;

import static com.github.lothar.katas.tennis.score.NormalScore.FOURTY;

import com.github.lothar.katas.tennis.Player;
import com.github.lothar.katas.tennis.Players;

public class NormalPoint extends AbstractPoint {

    public NormalPoint(Players players) {
        super(players);
    }

    @Override
    public void wonBy(Player player) {
        Player opponent = players.opponent(player);
        if (opponent.hasAdvantage()) {
            opponent.setScore(FOURTY);
        } else {
            player.incrementScore();
        }
    }

}
