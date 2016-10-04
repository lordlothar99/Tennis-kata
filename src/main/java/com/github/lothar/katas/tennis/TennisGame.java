package com.github.lothar.katas.tennis;

import static com.github.lothar.katas.tennis.Score.ADVANTAGE;
import static com.github.lothar.katas.tennis.Score.FOURTY;

public class TennisGame {

    private Player player1;
    private Player player2;

    public TennisGame(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public String getScores() {
        String games = player1.getGamesWon() + "-" + player2.getGamesWon();
        String scores = player1.getScore() + "-" + player2.getScore();
        return games + " ; " + scores;
    }

    public Score getScore(Player player) {
        return player.getScore();
    }

    public void scores(Player player) {
        Score score = player.getScore();
        if (opponentHasAdvantage(player)) {
            player1.setScore(FOURTY);
            player2.setScore(FOURTY);
        } else if (FOURTY.equals(score) && !isDeuce() //
                || ADVANTAGE.equals(score)) {
            winsGame(player);
        } else {
            player.setScore(player.getScore().next());
        }
    }

    private boolean opponentHasAdvantage(Player player) {
        Player opponent = opponent(player);
        return ADVANTAGE.equals(opponent.getScore());
    }

    private Player opponent(Player player) {
        return player1.equals(player) ? player2 : player1;
    }

    private boolean isDeuce() {
        return FOURTY.equals(player1.getScore()) //
                && FOURTY.equals(player2.getScore());
    }

    private void winsGame(Player player) {
        player.incrementGamesWon();
        player1.resetScore();
        player2.resetScore();
    }

    public int getGamesWon(Player player) {
        return player.getGamesWon();
    }

}
