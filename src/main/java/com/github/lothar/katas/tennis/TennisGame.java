package com.github.lothar.katas.tennis;

import static com.github.lothar.katas.tennis.SetsToWin.THREE;
import static com.github.lothar.katas.tennis.score.NormalScore.FOURTY;
import static java.util.Optional.ofNullable;

import java.util.Optional;

import com.github.lothar.katas.tennis.calculator.Advantage;
import com.github.lothar.katas.tennis.calculator.Deuce;
import com.github.lothar.katas.tennis.calculator.Normal;
import com.github.lothar.katas.tennis.calculator.ScoreCalculator;
import com.github.lothar.katas.tennis.exception.MatchIsOverException;
import com.github.lothar.katas.tennis.printer.ScorePrinter;
import com.github.lothar.katas.tennis.score.Score;

public class TennisGame {

    public static final int GAMES_COUNT_TO_WIN_A_SET = 6;
    public static final int MIN_POINTS_TO_WIN_TIE_BREAK = 7;
    private SetsToWin gameType;
    private Players players;

    public TennisGame(String player1Name, String player2Name) {
        this(player1Name, player2Name, THREE);
    }

    public TennisGame(String player1Name, String player2Name, SetsToWin gameType) {
        this.gameType = gameType;
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
        return isDeuce() ? new Deuce() //
                : players.playerWithAdvantage() //
                        .map(p -> (ScoreCalculator) new Advantage(players, p)) //
                        .orElse(new Normal(players));
    }

    private boolean isDeuce() {
        return players.stream() //
                .allMatch(p -> FOURTY.equals(p.getScore()));
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
        return ofNullable(!isMatchOver() ? null : players.playerWithMostSetsWon());
    }

    public boolean isMatchOver() {
        return isTieBreak() //
                && players.haveAtLeast2PointsOfDifferenceInTieBreak() //
                && players.existPlayerWithEnoughPointsToWinTieBreak() //
                || gameType.intValue() == players.getSetsWonSum();
    }

    public boolean isTieBreak() {
        return players.stream() //
                .allMatch(p -> p.getGamesWon() == GAMES_COUNT_TO_WIN_A_SET);
    }
}
