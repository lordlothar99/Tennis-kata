package com.github.lothar.katas.tennis;

public class TennisGame {

    private Player player1;
    private Player player2;

    public TennisGame(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public String getScores() {
        return player1.getScore() + "-" + player2.getScore();
    }

    public Score getScore(Player player) {
        return player.getScore();
    }

    public void scores(Player player) {
        player.scores();
    }

}
