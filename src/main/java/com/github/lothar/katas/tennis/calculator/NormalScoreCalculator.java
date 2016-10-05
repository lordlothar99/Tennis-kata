package com.github.lothar.katas.tennis.calculator;

import static com.github.lothar.katas.tennis.score.NormalScore.FOURTY;

import com.github.lothar.katas.tennis.Player;
import com.github.lothar.katas.tennis.Players;
import com.github.lothar.katas.tennis.SetsToWin;

public class NormalScoreCalculator extends AbstractCalculator {

    public NormalScoreCalculator(Players players, SetsToWin setsToWin) {
        super(players, setsToWin);
    }

    protected boolean isGamePointFor(Player player) {
        return player.hasAdvantage() //
                || FOURTY.equals(player.getScore()) //
                        && !opponent(player).hasAdvantage()
                        && !FOURTY.equals(opponent(player).getScore());
    }

    protected void winsThePoint(Player player) {
        if (opponent(player).hasAdvantage()) {
            opponent(player).setScore(FOURTY);
        } else {
            player.incrementScore();
        }
    }
}
