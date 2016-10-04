package com.github.lothar.katas.tennis;

import static com.github.lothar.katas.tennis.Score.FOURTY;

public class TennisGame {

    private Player player1;
    private Player player2;
    private ScoreCalculator scoreCalculator = new Normal();

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
        scoreCalculator.scores(player);
        updateState();
    }

    private void updateState() {
        if (isDeuce()) {
            scoreCalculator = new Deuce();
        } else if (player1.hasAdvantage()) {
            scoreCalculator = new Advantage(player1);
        } else if (player2.hasAdvantage()) {
            scoreCalculator = new Advantage(player2);
        } else {
            scoreCalculator = new Normal();
        }
    }

    private boolean isDeuce() {
        return FOURTY.equals(player1.getScore()) //
                && FOURTY.equals(player2.getScore());
    }

    public int getGamesWon(Player player) {
        return player.getGamesWon();
    }

    private void winsGame(Player player) {
        player.incrementGamesWon();
        player1.resetScore();
        player2.resetScore();
    }

    private interface ScoreCalculator {
        void scores(Player player);
    }

    private class Normal implements ScoreCalculator {
        @Override
        public void scores(Player player) {
            if (FOURTY.equals(player.getScore())) {
                winsGame(player);
            } else {
                player.incrementScore();
            }
        }
    }

    private class Deuce implements ScoreCalculator {
        @Override
        public void scores(Player player) {
            player.incrementScore();
        }
    }

    private class Advantage implements ScoreCalculator {
        private Player playerWithAdvantage;

        public Advantage(Player playerWithAdvantage) {
            this.playerWithAdvantage = playerWithAdvantage;
        }

        @Override
        public void scores(Player player) {
            if (playerWithAdvantage.equals(player)) {
                winsGame(player);
            } else {
                player1.setScore(FOURTY);
                player2.setScore(FOURTY);
            }
        }
    }
}
