package com.github.lothar.katas.tennis;

import static com.github.lothar.katas.tennis.Score.FOURTY;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class TennisGame {

    private Map<String, Player> players = new HashMap<>();

    public TennisGame(String player1, String player2) {
        players.put(player1, new Player(player1));
        players.put(player2, new Player(player2));
    }

    public String getScoreBoard() {
        return new ScoreBoard(players.values()).toString();
    }

    public Score getScore(String player) {
        return getPlayer(player).getScore();
    }

    public int getGamesWonInSet(String player, int set) {
        return getPlayer(player).getGamesWonInSet(set);
    }

    Player getPlayer(String player) {
        return players.get(player);
    }

    public void scores(String player) {
        ScoreCalculator scoreCalculator = getCalculator();
        scoreCalculator.playerScores(getPlayer(player));
    }

    private ScoreCalculator getCalculator() {
        return isDeuce() ? new Deuce() //
                : playerWithAdvantage() //
                        .map(p -> (ScoreCalculator) new Advantage(p)) //
                        .orElse(new Normal());
    }

    private Optional<Player> playerWithAdvantage() {
        return players.values().stream() //
                .filter(p -> p.hasAdvantage()) //
                .findFirst();
    }

    private boolean isDeuce() {
        return players.values().stream() //
                .allMatch(p -> FOURTY.equals(p.getScore()));
    }

    public int getGamesWon(String player) {
        return getPlayer(player).getGamesWon();
    }

    private void winsGame(Player player) {
        if (isSetPointFor(player)) {
            player.incrementGamesWon();
            player.incrementSetWon();
            players.values().stream() //
                    .forEach(p -> p.newSet());
        } else {
            player.incrementGamesWon();
            players.values().stream() //
                    .forEach(p -> p.newGame());
        }
    }

    private boolean isSetPointFor(Player player) {
        return player.getGamesWon() == 5 && isGamePointFor(player);
    }

    private boolean isGamePointFor(Player player) {
        return (!isDeuce() && FOURTY.equals(player.getScore())) //
                || player.hasAdvantage();
    }

    public int getSetsWon(String player) {
        return getPlayer(player).getSetsWon();
    }

    private interface ScoreCalculator {
        void playerScores(Player player);
    }

    private class Normal implements ScoreCalculator {
        @Override
        public void playerScores(Player player) {
            if (FOURTY.equals(player.getScore())) {
                winsGame(player);
            } else {
                player.incrementScore();
            }
        }
    }

    private class Deuce implements ScoreCalculator {
        @Override
        public void playerScores(Player player) {
            player.incrementScore();
        }
    }

    private class Advantage implements ScoreCalculator {
        private Player playerWithAdvantage;

        public Advantage(Player playerWithAdvantage) {
            this.playerWithAdvantage = playerWithAdvantage;
        }

        @Override
        public void playerScores(Player player) {
            if (playerWithAdvantage.equals(player)) {
                winsGame(player);
            } else {
                players.values().stream() //
                        .forEach(p -> p.setScore(FOURTY));
            }
        }
    }
}
