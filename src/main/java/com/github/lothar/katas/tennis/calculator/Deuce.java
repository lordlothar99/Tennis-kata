package com.github.lothar.katas.tennis.calculator;

import com.github.lothar.katas.tennis.Player;

public class Deuce implements ScoreCalculator {
    @Override
    public void playerScores(Player player) {
        player.incrementScore();
    }
}