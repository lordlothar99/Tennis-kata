package com.github.lothar.katas.tennis;

import java.util.Collection;
import java.util.function.Consumer;

public abstract class AbstractScoreCalculator implements ScoreCalculator {

    protected Collection<Player> players;

    public AbstractScoreCalculator(Collection<Player> players) {
        this.players = players;
    }

    protected void winsGame(Player player) {
        Consumer<Player> newGamePreparer = Player::newGame;

        if (isSetPointFor(player)) {
            player.incrementSetWon();
            Consumer<Player> newSetPreparer = Player::newSet;
            newGamePreparer = newSetPreparer.andThen(newGamePreparer);
        }

        player.incrementGamesWon();

        if (isTieBreak()) {
            newGamePreparer = newGamePreparer.andThen(Player::setupTieBreak);
        }
        players.stream().forEach(newGamePreparer);
    }

    public boolean isTieBreak() {
        return players.stream() //
                .allMatch(p -> p.getGamesWon() == 6);
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
