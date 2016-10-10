package com.github.lothar.katas.tennis.calculator;

import static com.github.lothar.katas.tennis.TennisGame.MIN_POINTS_TO_WIN_TIE_BREAK;

import com.github.lothar.katas.tennis.Player;
import com.github.lothar.katas.tennis.Players;
import com.github.lothar.katas.tennis.GameType;
import com.github.lothar.katas.tennis.score.TieBreakScore;

public class TieBreakPointAnalyzer extends AbstractPointAnalyzer {

    public TieBreakPointAnalyzer(Players players, GameType gameType) {
        super(players, gameType);
    }

    protected boolean isGamePointFor(Player player) {
        int playerScore = tieBreakScore(player);
        int opponentScore = tieBreakScore(players.opponent(player));

        return (playerScore + 1) >= MIN_POINTS_TO_WIN_TIE_BREAK //
                && playerScore > opponentScore;
    }

    private int tieBreakScore(Player player) {
        return ((TieBreakScore) player.getScore()).intValue();
    }
}
