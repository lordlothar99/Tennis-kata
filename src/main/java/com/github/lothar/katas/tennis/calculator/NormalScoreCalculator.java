package com.github.lothar.katas.tennis.calculator;

import static com.github.lothar.katas.tennis.score.NormalScore.FOURTY;

import com.github.lothar.katas.tennis.Player;
import com.github.lothar.katas.tennis.Players;
import com.github.lothar.katas.tennis.SetsToWin;

public class NormalScoreCalculator extends AbstractCalculator {

    public NormalScoreCalculator(Players players, SetsToWin setsToWin) {
        super(players, setsToWin);
    }

    protected boolean isSetPointFor(Player player) {
        return super.isSetPointFor(player) //
                && player.getGamesWon() > opponent(player).getGamesWon(); //
    }

    protected boolean isGamePointFor(Player player) {
        return player.hasAdvantage() //
                || FOURTY.equals(player.getScore()) //
                        && !opponent(player).hasAdvantage()
                        && !FOURTY.equals(opponent(player).getScore());
    }

    protected void winsNormalPoint(Player player) {
        Player opponent = opponent(player);
        if (opponent.hasAdvantage()) {
            opponent.setScore(FOURTY);
        } else {
            super.winsNormalPoint(player);
        }
    }
}
