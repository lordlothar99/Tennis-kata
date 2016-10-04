package com.github.lothar.katas.tennis;

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
        return player.isGamePoint() //
                && player.hasAtLeastOneGameMoreThan(opponent(player)) //
                && player.hasWonAtLeastFiveGames(); //
    }

    private Player opponent(Player player) {
        return players.stream() //
                .filter(p -> !player.equals(p)) //
                .findFirst().get();
    }
}
