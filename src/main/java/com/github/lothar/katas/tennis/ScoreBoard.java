package com.github.lothar.katas.tennis;

import static java.util.stream.Collectors.joining;
import static java.util.stream.IntStream.range;

import java.util.Collection;

class ScoreBoard {

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
        return line("Player", "Set", "Score");
    }

    private String scoreLine(Player player) {
        return line(player.getName(), player.getGamesWon(), player.getScore());
    }

    private String line(String player, Object set, Object score) {
        int maxPlayerNameLength = maxPlayerNameLength();
        return "| " + //
                fillToLength(player, maxPlayerNameLength) + //
                " | " + //
                fillToLength(set, "Set".length()) + //
                " | " + //
                fillToLength(score, "Score".length()) + //
                " |\n";


    }

    private int maxPlayerNameLength() {
        return players.stream() //
                .mapToInt(p -> p.getName().length()) //
                .max() //
                .getAsInt();
    }

    private static String fillToLength(Object string, int length) {
        int spacesCount = length - string.toString().length();
        return string + range(0, spacesCount) //
                .mapToObj($ -> " ") //
                .collect(joining(""));
    }
}
