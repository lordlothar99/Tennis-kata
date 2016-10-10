package com.github.lothar.katas.tennis;

import java.util.Optional;

import com.github.lothar.katas.tennis.calculator.DefaultPointAnalyzer;
import com.github.lothar.katas.tennis.calculator.Point;
import com.github.lothar.katas.tennis.calculator.PointAnalyzer;
import com.github.lothar.katas.tennis.calculator.TieBreakPointAnalyzer;
import com.github.lothar.katas.tennis.exception.MatchIsOverException;
import com.github.lothar.katas.tennis.printer.ScorePrinter;
import com.github.lothar.katas.tennis.score.Score;

public class TennisGame {

    public static final int GAMES_COUNT_TO_WIN_A_SET = 6;
    public static final int MIN_POINTS_TO_WIN_TIE_BREAK = 7;
    private GameType gameType;
    private Players players;

    public TennisGame(String player1Name, String player2Name, GameType gameType) {
        this.gameType = gameType;
        players = new Players(player1Name, player2Name);
    }

    public void scores(String playerName) {
        if (isMatchOver()) {
            throw new MatchIsOverException();
        }
        Player player = getPlayer(playerName);
        Point point = pointAnalyzer().pointFor(player);
        point.wonBy(player);
    }

    private PointAnalyzer pointAnalyzer() {
        if (isTieBreak()) {
            return new TieBreakPointAnalyzer(players, gameType);
        } else {
            return new DefaultPointAnalyzer(players, gameType);
        }
    }

    public Score<?> getScore(String playerName) {
        return getPlayer(playerName).getScore();
    }

    public int getGamesWonInSet(String playerName, int set) {
        return getPlayer(playerName).getGamesWonInSet(set);
    }

    public int getGamesWon(String playerName) {
        return getPlayer(playerName).getGamesWon();
    }

    public int getSetsWon(String playerName) {
        return getPlayer(playerName).getSetsWon();
    }

    Player getPlayer(String playerName) {
        return players.get(playerName);
    }

    public String getWinnerName() {
        return getWinner().map(Player::getName).orElse(null);
    }

    private Optional<Player> getWinner() {
        return players.getWinner();
    }

    public boolean isMatchOver() {
        return getWinner().isPresent();
    }

    public boolean isTieBreak() {
        return players.areInTieBreak();
    }

    public String toString() {
        return new ScorePrinter(players, getWinner()).toString();
    }
}
