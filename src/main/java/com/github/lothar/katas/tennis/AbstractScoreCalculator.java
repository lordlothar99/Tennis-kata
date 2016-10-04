package com.github.lothar.katas.tennis;

import static com.github.lothar.katas.tennis.Score.FOURTY;

import java.util.Collection;

public abstract class AbstractScoreCalculator implements ScoreCalculator {

    protected Collection<Player> players;

    public AbstractScoreCalculator(Collection<Player> players) {
        this.players = players;
    }

    protected void winsGame(Player player) {
        if (isSetPointFor(player)) {
            player.incrementGamesWon();
            player.incrementSetWon();
            players.stream() //
                    .forEach(p -> p.newSet());
        } else {
            player.incrementGamesWon();
            players.stream() //
                    .forEach(p -> p.newGame());
        }
    }

    private boolean isSetPointFor(Player player) {
        return player.getGamesWon() == (TennisGame.GAMES_COUNT_TO_WIN_A_SET - 1) //
                && isGamePointFor(player);
    }

    private boolean isGamePointFor(Player player) {
        return FOURTY.equals(player.getScore()) //
                || player.hasAdvantage();
    }
}