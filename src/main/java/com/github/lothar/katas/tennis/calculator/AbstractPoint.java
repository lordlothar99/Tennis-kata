package com.github.lothar.katas.tennis.calculator;

import com.github.lothar.katas.tennis.Players;

public abstract class AbstractPoint implements Point {

    protected Players players;

    public AbstractPoint(Players players) {
        this.players = players;
    }
}
