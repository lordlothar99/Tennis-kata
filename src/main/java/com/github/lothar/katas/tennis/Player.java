package com.github.lothar.katas.tennis;

import static com.github.lothar.katas.tennis.score.NormalScore.ADVANTAGE;
import static com.github.lothar.katas.tennis.score.NormalScore.FOURTY;
import static com.github.lothar.katas.tennis.score.NormalScore.ZERO;

import java.util.Stack;

import com.github.lothar.katas.tennis.score.Score;
import com.github.lothar.katas.tennis.score.TieBreakScore;

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

    public void setupNewGame() {
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

    public void setupNewSet() {
        gamesWonBySet.add(0);
    }

    public void setupTieBreak() {
        score = TieBreakScore.ZERO;
    }

    public boolean hasWonAtLeastFiveGames() {
        return getGamesWon() >= (TennisGame.GAMES_COUNT_TO_WIN_A_SET - 1);
    }

    public boolean hasAtLeastOneGameMoreThan(Player opponent) {
        return getGamesWon() > opponent.getGamesWon();
    }

    public boolean isGamePoint() {
        return FOURTY.equals(score) //
                || hasAdvantage();
    }
}
