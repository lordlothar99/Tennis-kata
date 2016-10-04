package com.github.lothar.katas.tennis;

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
        if (Score.FOURTEEN.equals(score)) {
            winsGame(player);
        } else {
            player.incrementScore();
        }
    }

    private void winsGame(Player player) {
        player.incrementGamesWon();
        player1.resetScore();
        player2.resetScore();
    }

}
