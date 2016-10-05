package com.github.lothar.katas.tennis;

import static com.github.lothar.katas.tennis.TennisGame.MIN_POINTS_TO_WIN_TIE_BREAK;
import static java.lang.Math.abs;
import static java.util.Comparator.comparingInt;

import java.util.Optional;
import java.util.stream.Stream;

import com.github.lothar.katas.tennis.score.TieBreakScore;

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

    public Optional<Player> playerWithAdvantage() {
        return stream() //
                .filter(Player::hasAdvantage) //
                .findFirst();
    }

    public Player playerWithMostSetsWon() {
        return stream() //
                .max(comparingInt(Player::getSetsWon)) //
                .get();
    }

    public int getSetsWonSum() {
        return player1.getSetsWon() + player2.getSetsWon();
    }

    public boolean haveAtLeast2PointsOfDifferenceInTieBreak() {
        int player1Score = tieBreakScore(player1);
        int player2Score = tieBreakScore(player2);

        return abs(player2Score - player1Score) >= 2;
    }

    public boolean existPlayerWithEnoughPointsToWinTieBreak() {
        return stream() //
                .anyMatch(p -> tieBreakScore(p) >= MIN_POINTS_TO_WIN_TIE_BREAK);
    }

    public int tieBreakScore(Player player) {
        return ((TieBreakScore) player.getScore()).intValue();
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
}
