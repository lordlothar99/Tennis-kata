package com.github.lothar.katas.tennis;

import static com.github.lothar.katas.tennis.TennisGame.GAMES_COUNT_TO_WIN_A_SET;

import java.util.Optional;
import java.util.stream.Stream;

public class Players {

    private Player player1;
    private Player player2;

    Players(String player1Name, String player2Name) {
        this.player1 = new Player(player1Name);
        this.player2 = new Player(player2Name);
    }

    public Stream<Player> stream() {
        return Stream.of(player1, player2);
    }

    public Player get(String playerName) {
        return player1.getName().equals(playerName) //
                ? player1 //
                : player2.getName().equals(playerName) //
                        ? player2 //
                        : null;
    }

    public int getSetsWonSum() {
        return player1.getSetsWon() + player2.getSetsWon();
    }

    public int getSetsCount() {
        return player1.getSetsCount();
    }

    public Player opponent(Player player) {
        return player1.equals(player) ? player2 : player1;
    }

    public Optional<Player> getWinner() {
        if (player1.isWinner()) {
            return Optional.of(player1);
        } else if (player2.isWinner()) {
            return Optional.of(player2);
        } else {
            return Optional.empty();
        }
    }

    public boolean areInTieBreak() {
        return player1.getGamesWon() == GAMES_COUNT_TO_WIN_A_SET //
                && player2.getGamesWon() == GAMES_COUNT_TO_WIN_A_SET;
    }

    public void setupTieBreak() {
        stream().forEach(Player::setupTieBreak);
    }

    public void resetScore() {
        stream().forEach(Player::resetScore);
    }

    public void setupNewSet() {
        stream().forEach(Player::setupNewSet);
    }
}
