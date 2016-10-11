package com.github.lothar.katas.tennis.point.analyzer;

import static com.github.lothar.katas.tennis.TennisGame.GAMES_COUNT_TO_WIN_A_SET;

import com.github.lothar.katas.tennis.Player;
import com.github.lothar.katas.tennis.Players;
import com.github.lothar.katas.tennis.point.GamePoint;
import com.github.lothar.katas.tennis.point.MatchPoint;
import com.github.lothar.katas.tennis.point.NormalPoint;
import com.github.lothar.katas.tennis.point.Point;
import com.github.lothar.katas.tennis.point.SetPoint;
import com.github.lothar.katas.tennis.GameType;

public abstract class AbstractPointAnalyzer implements PointAnalyzer {

    protected Players players;
    private GameType gameType;

    public AbstractPointAnalyzer(Players players, GameType gameType) {
        this.players = players;
        this.gameType = gameType;
    }

    @Override
    public Point pointFor(Player player) {
        return isMatchPointFor(player) ? new MatchPoint(players) //
             : isSetPointFor(player) ? new SetPoint(players) //
             : isGamePointFor(player) ? new GamePoint(players) //
             : new NormalPoint(players);
    }

    private boolean isMatchPointFor(Player player) {
        return isSetPointFor(player) //
                && (player.getSetsWon() + 1) >= gameType.setsCountToWinTheMatch();
    }

    protected boolean isSetPointFor(Player player) {
        return isGamePointFor(player) //
                && (player.getGamesWon() + 1) >= GAMES_COUNT_TO_WIN_A_SET;
    }

    protected abstract boolean isGamePointFor(Player player);

}
