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
    public void playerScores(Player player) {
        if (isMatchPointFor(player)) {
            winsTheMatch(player);

        } else if (isSetPointFor(player)) {
            winsTheSet(player);

        } else if (isGamePointFor(player)) {
            winsTheGame(player);

        } else {
            winsNormalPoint(player);
        }
    }

    private boolean isMatchPointFor(Player player) {
        return isSetPointFor(player) && //
                (player.getSetsWon() + 1) >= setsToWin.intValue();
    }

    private void winsTheMatch(Player player) {
        player.incrementGamesWon();
        player.incrementSetWon();
        player.setWinner();
        players.resetScore();
    }

    protected boolean isSetPointFor(Player player) {
        return isGamePointFor(player) //
                && player.getGamesWon() + 1 >= GAMES_COUNT_TO_WIN_A_SET;
    }

    private void winsTheSet(Player player) {
        player.incrementGamesWon();
        player.incrementSetWon();
        players.setupNewSet();
        players.resetScore();
    }

    protected abstract boolean isGamePointFor(Player player);

    private void winsTheGame(Player player) {
        player.incrementGamesWon();
        if (players.areInTieBreak()) {
            players.setupTieBreak();
        } else {
            players.resetScore();
        }
    }

    protected void winsNormalPoint(Player player) {
        player.incrementScore();
    }

    protected Player opponent(Player player) {
        return players.opponent(player);
    }

}
