package com.github.lothar.katas.tennis.calculator;

import static com.github.lothar.katas.tennis.TennisGame.GAMES_COUNT_TO_WIN_A_SET;

import java.util.function.Consumer;

import com.github.lothar.katas.tennis.Player;
import com.github.lothar.katas.tennis.Players;

public abstract class AbstractScoreCalculator implements ScoreCalculator {

    protected Players players;

    public AbstractScoreCalculator(Players players) {
        this.players = players;
    }

    protected void winsGame(Player player) {
        Consumer<Player> newGamePreparer = Player::setupNewGame;

        if (isSetPointFor(player)) {
            player.incrementSetWon();
            Consumer<Player> newSetPreparer = Player::setupNewSet;
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
                .allMatch(p -> p.getGamesWon() == GAMES_COUNT_TO_WIN_A_SET);
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
