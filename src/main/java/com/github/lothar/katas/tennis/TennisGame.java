package com.github.lothar.katas.tennis;

public class TennisGame {

    private Score player1Score = Score.ZERO;

    public Score getPlayer1Score() {
        return player1Score;
    }

    public Score getPlayer2Score() {
        return Score.ZERO;
    }

    public void player1Scores() {
        player1Score = Score.FIFTEEN;
    }

}
