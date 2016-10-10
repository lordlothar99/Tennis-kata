package com.github.lothar.katas.tennis;

public enum GameType {

    TWO_SETS(2), //
    THREE_SETS(3);

    private int value;

    private GameType(int value) {
        this.value = value;
    }

    public int setsCountToWinTheMatch() {
        return value;
    }
}
