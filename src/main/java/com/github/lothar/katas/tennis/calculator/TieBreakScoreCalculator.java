package com.github.lothar.katas.tennis.calculator;

import static com.github.lothar.katas.tennis.TennisGame.MIN_POINTS_TO_WIN_TIE_BREAK;

import com.github.lothar.katas.tennis.Player;
import com.github.lothar.katas.tennis.Players;
import com.github.lothar.katas.tennis.SetsToWin;
import com.github.lothar.katas.tennis.score.TieBreakScore;

public class TieBreakScoreCalculator extends AbstractCalculator {

    public TieBreakScoreCalculator(Players players, SetsToWin setsToWin) {
        super(players, setsToWin);
    }

    protected boolean isGamePointFor(Player player) {
        int tieBreakScore = tieBreakScore(player);
        int opponentScore = tieBreakScore(opponent(player));

        return (tieBreakScore + 1) >= MIN_POINTS_TO_WIN_TIE_BREAK
                && tieBreakScore - opponentScore > 0;
    }

    private int tieBreakScore(Player player) {
        return ((TieBreakScore) player.getScore()).intValue();
    }
}
