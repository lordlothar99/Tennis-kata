package com.github.lothar.katas.tennis;

public enum GameType {

    TWO_SETS(2), //
    THREE_SETS(3);

    private int setsCountToWinTheMatch;

    private GameType(int setsCountToWinTheMatch) {
        this.setsCountToWinTheMatch = setsCountToWinTheMatch;
    }

    public int setsCountToWinTheMatch() {
        return setsCountToWinTheMatch;
    }
}
