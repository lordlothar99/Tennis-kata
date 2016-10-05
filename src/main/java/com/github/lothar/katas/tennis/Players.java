package com.github.lothar.katas.tennis;

import static com.github.lothar.katas.tennis.TennisGame.MIN_POINTS_TO_WIN_TIE_BREAK;
import static java.util.Comparator.comparingInt;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import com.github.lothar.katas.tennis.score.TieBreakScore;

public class Players {

    private Map<String, Player> players = new LinkedHashMap<>();

    Players(String player1, String player2) {
        players.put(player1, new Player(player1));
        players.put(player2, new Player(player2));
    }

    public Stream<Player> stream() {
        return players.values().stream();
    }

    public Player get(String player) {
        return players.get(player);
    }

    public Optional<Player> playerWithAdvantage() {
        return stream() //
                .filter(Player::hasAdvantage) //
                .findFirst();
    }

    public Player playerWithMostSetsWon() {
        return stream() //
                .max(comparingInt(Player::getSetsWon)) //
                .get();
    }

    public int getSetsWonSum() {
        return stream() //
                .mapToInt(Player::getSetsWon) //
                .sum();
    }

    public boolean haveAtLeast2PointsOfDifferenceInTieBreak() {
        List<Player> playersList = new ArrayList<>(players.values());
        Player player1 = playersList.get(0);
        Player player2 = playersList.get(1);
        int player1Score = ((TieBreakScore) player1.getScore()).intValue();
        int player2Score = ((TieBreakScore) player2.getScore()).intValue();

        return Math.abs(player2Score - player1Score) >= 2;
    }

    public boolean existPlayerWithEnoughPointsToWinTieBreak() {
        return stream() //
                .anyMatch(p -> new TieBreakScore(MIN_POINTS_TO_WIN_TIE_BREAK).equals(p.getScore()));
    }
}
