package com.github.lothar.katas.tennis;

import static com.github.lothar.katas.tennis.GameType.THREE_SETS;
import static com.github.lothar.katas.tennis.Score.FOURTY;
import static java.util.Comparator.comparingInt;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class TennisGame {

    private GameType gameType;
    private Map<String, Player> players = new HashMap<>();

    public TennisGame(String player1, String player2) {
        this(player1, player2, THREE_SETS);
    }

    public TennisGame(String player1, String player2, GameType gameType) {
        this.gameType = gameType;
        players.put(player1, new Player(player1));
        players.put(player2, new Player(player2));
    }

    public String getScoreBoard() {
        return new ScoreBoard(players.values(), gameType, getWinnerPlayer()).toString();
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
        if (isMatchOver()) {
            throw new MatchIsOverException();
        }
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

    public int getSetsWon(String player) {
        return getPlayer(player).getSetsWon();
    }

    public String getWinner() {
        return getWinnerPlayer().map(Player::getName).orElse(null);
    }

    private Optional<Player> getWinnerPlayer() {
        if (!isMatchOver()) {
            return Optional.empty();
        }
        return Optional.of(players.values().stream() //
                .max(comparingInt(Player::getSetsWon)) //
                .get());
    }

    private boolean isMatchOver() {
        return gameType.setCount() == players.values().stream() //
                .mapToInt(Player::getSetsWon) //
                .sum();
    }

    private interface ScoreCalculator {
        void playerScores(Player player);
    }

    private class Normal extends AbstractScoreCalculator {
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

    private class Advantage extends AbstractScoreCalculator {
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

    private abstract class AbstractScoreCalculator implements ScoreCalculator {
        protected void winsGame(Player player) {
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
    }
}
