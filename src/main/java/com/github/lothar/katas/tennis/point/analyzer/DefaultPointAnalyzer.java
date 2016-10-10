package com.github.lothar.katas.tennis.point.analyzer;

import static com.github.lothar.katas.tennis.score.NormalScore.FOURTY;

import com.github.lothar.katas.tennis.GameType;
import com.github.lothar.katas.tennis.Player;
import com.github.lothar.katas.tennis.Players;
import com.github.lothar.katas.tennis.score.NormalScore;

public class DefaultPointAnalyzer extends AbstractPointAnalyzer {

    public DefaultPointAnalyzer(Players players, GameType gameType) {
        super(players, gameType);
    }

    protected boolean isSetPointFor(Player player) {
        return super.isSetPointFor(player) //
                && player.getGamesWon() > players.opponent(player).getGamesWon(); //
    }

    protected boolean isGamePointFor(Player player) {
        NormalScore playerScore = player.getScore();
        NormalScore opponentScore = players.opponent(player).getScore();

        return playerScore.compareTo(FOURTY) >= 0 //
                && playerScore.compareTo(opponentScore) > 0;
    }
}
