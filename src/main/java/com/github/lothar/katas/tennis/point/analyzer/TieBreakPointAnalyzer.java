package com.github.lothar.katas.tennis.point.analyzer;

import static com.github.lothar.katas.tennis.TennisGame.MIN_POINTS_TO_WIN_TIE_BREAK;

import com.github.lothar.katas.tennis.GameType;
import com.github.lothar.katas.tennis.Player;
import com.github.lothar.katas.tennis.Players;
import com.github.lothar.katas.tennis.score.TieBreakScore;

public class TieBreakPointAnalyzer extends AbstractPointAnalyzer {

    private static final TieBreakScore MIN_SCORE_BEFORE_WIN_THE_SET =
            new TieBreakScore(MIN_POINTS_TO_WIN_TIE_BREAK - 1);

    public TieBreakPointAnalyzer(Players players, GameType gameType) {
        super(players, gameType);
    }

    protected boolean isGamePointFor(Player player) {
        TieBreakScore playerScore = player.getScore();
        TieBreakScore opponentScore = players.opponent(player).getScore();

        return playerScore.compareTo(MIN_SCORE_BEFORE_WIN_THE_SET) >= 0
                && playerScore.compareTo(opponentScore) > 0;
    }
}
