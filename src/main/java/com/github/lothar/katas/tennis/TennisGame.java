package com.github.lothar.katas.tennis;

public class TennisGame {

    private Score player1Score = Score.ZERO;
    private Score player2Score = Score.ZERO;

    public Score getPlayer1Score() {
        return player1Score;
    }

    public Score getPlayer2Score() {
        return player2Score;
    }

    public void player1Scores() {
        player1Score = player1Score.next();
    }

    public void player2Scores() {
        player2Score = player2Score.next();
    }

    public String getScores() {
        return player1Score + "-" + player2Score;
    }

}
