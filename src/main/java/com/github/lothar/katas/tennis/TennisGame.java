package com.github.lothar.katas.tennis;

import static com.github.lothar.katas.tennis.Score.FOURTY;

public class TennisGame {

    private Player player1;
    private Player player2;
    private GameState state = new NormalState();

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
        state.scores(player);
        updateState();
    }

    private void updateState() {
        if (isDeuce()) {
            state = new DeuceState();
        } else if (player1.hasAdvantage()) {
            state = new AdvantageState(player1);
        } else if (player2.hasAdvantage()) {
            state = new AdvantageState(player2);
        } else {
            state = new NormalState();
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

    private interface GameState {
        void scores(Player player);
    }

    private class NormalState implements GameState {
        @Override
        public void scores(Player player) {
            if (FOURTY.equals(player.getScore())) {
                winsGame(player);
            } else {
                player.incrementScore();
            }
        }
    }

    private class DeuceState implements GameState {
        @Override
        public void scores(Player player) {
            player.incrementScore();
        }
    }

    private class AdvantageState implements GameState {
        private Player playerWithAdvantage;

        public AdvantageState(Player playerWithAdvantage) {
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
