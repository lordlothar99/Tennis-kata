package com.github.lothar.katas.tennis;

import static com.github.lothar.katas.tennis.Score.ADVANTAGE;
import static com.github.lothar.katas.tennis.Score.ZERO;

import java.util.Stack;

class Player {

    private Score score = ZERO;
    private Stack<Integer> gamesWonBySet = new Stack<>();
    private int setsWon;
    private String name;

    public Player(String name) {
        this.name = name;
        gamesWonBySet.add(0);
    }

    public String getName() {
        return name;
    }

    public Score getScore() {
        return score;
    }

    public void incrementScore() {
        score = score.next();
    }

    public void newGame() {
        score = ZERO;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public int getGamesWon() {
        return gamesWonBySet.peek();
    }

    public void incrementGamesWon() {
        setGamesWon(getGamesWon() + 1);
    }

    public void setGamesWon(int gamesWon) {
        gamesWonBySet.set(gamesWonBySet.size() - 1, gamesWon);
    }

    public boolean hasAdvantage() {
        return ADVANTAGE.equals(score);
    }

    public int getGamesWonInSet(int set) {
        if (gamesWonBySet.size() < set) {
            return 0;
        } else {
            return gamesWonBySet.get(set - 1);
        }
    }

    public int getSetsWon() {
        return setsWon;
    }

    public void incrementSetWon() {
        setsWon++;
    }

    public void newSet() {
        gamesWonBySet.add(0);
        newGame();
    }
}
