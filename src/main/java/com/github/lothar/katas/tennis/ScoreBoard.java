package com.github.lothar.katas.tennis;

import static java.lang.Math.max;
import static java.util.stream.Collectors.joining;
import static java.util.stream.IntStream.range;

import java.util.Collection;

class ScoreBoard {

    private static final String PLAYER_COLUMN = "Player";
    private static final int PLAYER_COLUMN_LENGTH = PLAYER_COLUMN.length();
    private static final String SET_COLUMN = "Set";
    private static final int SET_COLUMN_LENGTH = SET_COLUMN.length();
    private static final String SCORE_COLUMN = "Score";
    private static final int SCORE_COLUMN_LENGTH = SCORE_COLUMN.length();
    private Collection<Player> players;

    public ScoreBoard(Collection<Player> players) {
        this.players = players;
    }

    @Override
    public String toString() {
        String header = header();
        String playerScores = players.stream() //
                .map(p -> scoreLine(p)) //
                .collect(joining(""));
        return header + playerScores;
    }

    private String header() {
        return line(PLAYER_COLUMN, SET_COLUMN, SCORE_COLUMN);
    }

    private String scoreLine(Player player) {
        return line(player.getName(), player.getGamesWon(), player.getScore());
    }

    private String line(String player, Object set, Object score) {
        int maxPlayerNameLength = maxPlayerNameLength();
        return "| " + //
                fillToLength(player, maxPlayerNameLength) + //
                " | " + //
                fillToLength(set, SET_COLUMN_LENGTH) + //
                " | " + //
                fillToLength(score, SCORE_COLUMN_LENGTH) + //
                " |\n";
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
