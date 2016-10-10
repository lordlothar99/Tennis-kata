package com.github.lothar.katas.tennis.calculator;

import static com.github.lothar.katas.tennis.score.NormalScore.FOURTY;

import com.github.lothar.katas.tennis.Player;
import com.github.lothar.katas.tennis.Players;
import com.github.lothar.katas.tennis.GameType;

public class DefaultPointAnalyzer extends AbstractPointAnalyzer {

    public DefaultPointAnalyzer(Players players, GameType gameType) {
        super(players, gameType);
    }

    protected boolean isSetPointFor(Player player) {
        return super.isSetPointFor(player) //
                && player.getGamesWon() > opponent(player).getGamesWon(); //
    }

    protected boolean isGamePointFor(Player player) {
        Player opponent = opponent(player);
        return player.hasAdvantage() //
                || FOURTY.equals(player.getScore()) //
                        && !opponent.hasAdvantage() && !FOURTY.equals(opponent.getScore());
    }
}
