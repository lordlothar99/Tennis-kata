package com.github.lothar.katas.tennis;

import static com.github.lothar.katas.tennis.GameType.THREE_SETS;
import static com.github.lothar.katas.tennis.NormalScore.FOURTY;

import java.util.Optional;

public class TennisGame {

    static final int GAMES_COUNT_TO_WIN_A_SET = 6;
    private GameType gameType;
    private Players players;

    public TennisGame(String player1, String player2) {
        this(player1, player2, THREE_SETS);
    }

    public TennisGame(String player1, String player2, GameType gameType) {
        this.gameType = gameType;
        players = new Players(player1, player2);
    }

    public String toString() {
        return new ScoreBoard(players, gameType, getWinnerPlayer()).toString();
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
        scoreCalculator().playerScores(getPlayer(player));
    }

    private ScoreCalculator scoreCalculator() {
        return isDeuce() ? new Deuce() //
                : players.playerWithAdvantage() //
                        .map(p -> (ScoreCalculator) new Advantage(players, p)) //
                        .orElse(new Normal(players));
    }

    private boolean isDeuce() {
        return players.stream() //
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
        return Optional.of(players.playerWithMostSetsWon());
    }

    private boolean isMatchOver() {
        return gameType.setCount() == players.stream() //
                .mapToInt(Player::getSetsWon) //
                .sum();
    }

    public boolean isTieBreak() {
        return players.stream() //
                .allMatch(p -> p.getGamesWon() == GAMES_COUNT_TO_WIN_A_SET);
    }
}
