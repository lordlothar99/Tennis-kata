package com.github.lothar.katas.tennis;

public class Deuce implements ScoreCalculator {
    @Override
    public void playerScores(Player player) {
        player.incrementScore();
    }
}