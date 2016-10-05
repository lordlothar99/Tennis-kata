package com.github.lothar.katas.tennis.calculator;

import static com.github.lothar.katas.tennis.TennisGame.GAMES_COUNT_TO_WIN_A_SET;

import com.github.lothar.katas.tennis.Player;
import com.github.lothar.katas.tennis.Players;
import com.github.lothar.katas.tennis.SetsToWin;
import com.github.lothar.katas.tennis.TennisGame;

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
            winsThePoint(player);
        }
    }

    private boolean isMatchPointFor(Player player) {
        return isSetPointFor(player) && //
                (players.getSetsWonSum() + 1) >= setsToWin.intValue();
    }

    private void winsTheMatch(Player player) {
        player.incrementGamesWon();
        player.incrementSetWon();
        player.setWinner();
        players.stream().forEach(Player::setupNewGame);
    }

    protected boolean isSetPointFor(Player player) {
        return isGamePointFor(player) //
                && player.getGamesWon() + 1 >= GAMES_COUNT_TO_WIN_A_SET //
                && (player.getGamesWon() > opponent(player).getGamesWon() || isTieBreak()); //
    }

    private void winsTheSet(Player player) {
        player.incrementGamesWon();
        player.incrementSetWon();
        players.stream().forEach(Player::setupNewSet);
        players.stream().forEach(Player::setupNewGame);
    }

    protected abstract boolean isGamePointFor(Player player);

    private void winsTheGame(Player player) {
        player.incrementGamesWon();
        if (isTieBreak()) {
            players.stream().forEach(Player::setupTieBreak);
        } else {
            players.stream().forEach(Player::setupNewGame);
        }
    }

    private boolean isTieBreak() {
        return players.stream() //
                .allMatch(p -> p.getGamesWon() == TennisGame.GAMES_COUNT_TO_WIN_A_SET);
    }

    protected void winsThePoint(Player player) {
        player.incrementScore();
    }

    protected Player opponent(Player player) {
        return players.opponent(player);
    }

}
