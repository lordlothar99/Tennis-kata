package com.github.lothar.katas.tennis.printer;

import static java.lang.Math.max;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;
import static java.util.stream.IntStream.rangeClosed;

import java.util.List;
import java.util.Optional;

import com.github.lothar.katas.tennis.GameType;
import com.github.lothar.katas.tennis.Player;
import com.github.lothar.katas.tennis.Players;

public class ScorePrinter {

    private static final String PLAYER_COLUMN = "Player";
    private static final int PLAYER_COLUMN_LENGTH = PLAYER_COLUMN.length();
    private static final String SET_COLUMN = "Set";
    private static final int SET_COLUMN_LENGTH = SET_COLUMN.length() + 2;
    private static final String SCORE_COLUMN = "Score";
    private static final String RESULT_COLUMN = "Result";
    private static final String WINNER = "WINNER";

    private Players players;
    private GameType gameType;
    private Optional<Player> winner;

    public ScorePrinter(Players players, GameType gameType, Optional<Player> winner) {
        this.players = players;
        this.gameType = gameType;
        this.winner = winner;
    }

    @Override
    public String toString() {
        return header() + players.stream() //
                .map(p -> scoreLine(p)) //
                .collect(joining(""));
    }

    private String header() {
        List<String> setsColumns = rangeClosed(1, gameType.setsCount()) //
                .mapToObj(set -> SET_COLUMN + " " + set) //
                .collect(toList());
        return line(PLAYER_COLUMN, setsColumns, lastColumnHeader());
    }

    private String lastColumnHeader() {
        return winner.isPresent() ? RESULT_COLUMN : SCORE_COLUMN;
    }

    private String scoreLine(Player player) {
        List<Integer> gamesWonBySets = rangeClosed(1, gameType.setsCount()) //
                .mapToObj(set -> player.getGamesWonInSet(set)) //
                .collect(toList());
        return line(player.getName(), gamesWonBySets, lastColumnValue(player));
    }

    private Object lastColumnValue(Player player) {
        if (winner.isPresent()) {
            return winner.get().equals(player) ? WINNER : "";
        } else {
            return player.getScore();
        }
    }

    private String line(String player, List<?> sets, Object score) {
        String setColumns = sets.stream() //
                .map(s -> fillToLength(s, SET_COLUMN_LENGTH)) //
                .collect(joining(" | "));
        String line = "| " + //
                fillToLength(player, maxPlayerNameLength()) + //
                " | " + //
                setColumns + //
                " | " + //
                fillToLength(score, lastColumnHeader().length()) + //
                " |\n";
        return line;
    }

    private int maxPlayerNameLength() {
        return max(PLAYER_COLUMN_LENGTH,
                players.stream() //
                        .mapToInt(p -> p.getName().length()) //
                        .max() //
                        .getAsInt());
    }

    private static String fillToLength(Object string, int length) {
        int spacesCount = length - string.toString().length();
        if (spacesCount <= 0) {
            return string.toString();
        }
        return string + range(0, spacesCount) //
                .mapToObj($ -> " ") //
                .collect(joining(""));
    }
}
