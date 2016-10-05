package com.github.lothar.katas.tennis;

import java.util.Optional;

import com.github.lothar.katas.tennis.calculator.NormalScoreCalculator;
import com.github.lothar.katas.tennis.calculator.ScoreCalculator;
import com.github.lothar.katas.tennis.calculator.TieBreakScoreCalculator;
import com.github.lothar.katas.tennis.exception.MatchIsOverException;
import com.github.lothar.katas.tennis.printer.ScorePrinter;
import com.github.lothar.katas.tennis.score.Score;

public class TennisGame {

    public static final int GAMES_COUNT_TO_WIN_A_SET = 6;
    public static final int MIN_POINTS_TO_WIN_TIE_BREAK = 7;
    private SetsToWin setsToWin;
    private Players players;

    public TennisGame(String player1Name, String player2Name, SetsToWin gameType) {
        this.setsToWin = gameType;
        players = new Players(player1Name, player2Name);
    }

    public String toString() {
        return new ScorePrinter(players, getWinner()).toString();
    }

    public Score getScore(String playerName) {
        return getPlayer(playerName).getScore();
    }

    public int getGamesWonInSet(String playerName, int set) {
        return getPlayer(playerName).getGamesWonInSet(set);
    }

    Player getPlayer(String playerName) {
        return players.get(playerName);
    }

    public void scores(String playerName) {
        if (isMatchOver()) {
            throw new MatchIsOverException();
        }
        scoreCalculator().playerScores(getPlayer(playerName));
    }

    private ScoreCalculator scoreCalculator() {
        if (isTieBreak()) {
            return new TieBreakScoreCalculator(players, setsToWin);
        } else {
            return new NormalScoreCalculator(players, setsToWin);
        }
    }

    public int getGamesWon(String playerName) {
        return getPlayer(playerName).getGamesWon();
    }

    public int getSetsWon(String playerName) {
        return getPlayer(playerName).getSetsWon();
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
}
