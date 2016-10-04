package com.github.lothar.katas.tennis;

import static java.lang.Math.max;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;
import static java.util.stream.IntStream.rangeClosed;

import java.util.Collection;
import java.util.List;

abstract class AbstractBoard {

    private static final String PLAYER_COLUMN = "Player";
    private static final int PLAYER_COLUMN_LENGTH = PLAYER_COLUMN.length();
    private static final String SET_COLUMN = "Set";
    private static final int SET_COLUMN_LENGTH = SET_COLUMN.length() + 2;
    private Collection<Player> players;
    private GameType gameType;

    public AbstractBoard(Collection<Player> players, GameType gameType) {
        this.players = players;
        this.gameType = gameType;
    }

    @Override
    public String toString() {
        return header() + players.stream() //
                .map(p -> scoreLine(p)) //
                .collect(joining(""));
    }

    private String header() {
        List<String> setsColumns = rangeClosed(1, gameType.setCount()) //
                .mapToObj(set -> SET_COLUMN + " " + set) //
                .collect(toList());
        return line(PLAYER_COLUMN, setsColumns, lastColumnHeader());
    }

    protected abstract String lastColumnHeader();

    private String scoreLine(Player player) {
        List<Integer> gamesWonBySets = rangeClosed(1, gameType.setCount()) //
                .mapToObj(set -> player.getGamesWonInSet(set)) //
                .collect(toList());
        return line(player.getName(), gamesWonBySets, lastColumnValue(player));
    }

    protected abstract Object lastColumnValue(Player player);

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
