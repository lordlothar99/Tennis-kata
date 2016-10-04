package com.github.lothar.katas.tennis;

import static com.github.lothar.katas.tennis.Score.ZERO;

public class Player {

    private Score score = ZERO;
    private int gamesWon = 0;

    public Score getScore() {
        return score;
    }

    public void incrementScore() {
        score = score.next();
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public void incrementGamesWon() {
        gamesWon++;
    }

    public void resetScore() {
        score = ZERO;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public boolean hasAdvantage() {
        return Score.ADVANTAGE.equals(score);
    }
}
