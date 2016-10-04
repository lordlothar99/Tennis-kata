package com.github.lothar.katas.tennis;

public enum GameType {

    ONE_SET(1), //
    THREE_SETS(3), //
    FIVE_SETS(5);

    private int value;

    private GameType(int value) {
        this.value = value;
    }

    public int setCount() {
        return value;
    }
}
