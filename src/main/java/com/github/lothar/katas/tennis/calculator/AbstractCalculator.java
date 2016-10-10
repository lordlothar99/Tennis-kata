package com.github.lothar.katas.tennis.calculator;

import static com.github.lothar.katas.tennis.TennisGame.GAMES_COUNT_TO_WIN_A_SET;

import com.github.lothar.katas.tennis.Player;
import com.github.lothar.katas.tennis.Players;
import com.github.lothar.katas.tennis.SetsToWin;

public abstract class AbstractCalculator implements ScoreCalculator {

    protected Players players;
    private SetsToWin setsToWin;

    public AbstractCalculator(Players players, SetsToWin setsToWin) {
        this.players = players;
        this.setsToWin = setsToWin;
    }

    @Override
    public void pointWonBy(Player player) {
        Point point = pointFor(player);
        point.wonBy(player);
    }

    private Point pointFor(Player player) {
        Point point;
        if (isMatchPointFor(player)) {
            point = new MatchPoint(players);

        } else if (isSetPointFor(player)) {
            point = new SetPoint(players);

        } else if (isGamePointFor(player)) {
            point = new GamePoint(players);

        } else {
            point = new NormalPoint(players);
        }
        return point;
    }

    private boolean isMatchPointFor(Player player) {
        return isSetPointFor(player) && //
                (player.getSetsWon() + 1) >= setsToWin.intValue();
    }

    protected boolean isSetPointFor(Player player) {
        return isGamePointFor(player) //
                && player.getGamesWon() + 1 >= GAMES_COUNT_TO_WIN_A_SET;
    }

    protected abstract boolean isGamePointFor(Player player);

    protected Player opponent(Player player) {
        return players.opponent(player);
    }

}
