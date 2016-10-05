package com.github.lothar.katas.tennis;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Players {

    private Map<String, Player> players = new LinkedHashMap<>();

    public Players(String player1, String player2) {
        players.put(player1, new Player(player1));
        players.put(player2, new Player(player2));
    }

    public Stream<Player> stream() {
        return players.values().stream();
    }

    public Player get(String player) {
        return players.get(player);
    }
}
