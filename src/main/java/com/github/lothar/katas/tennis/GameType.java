package com.github.lothar.katas.tennis;

public enum GameType {

    ONE_SET(1), //
    THREE_SETS(3), //
    FIVE_SETS(5);

    private int setsCount;

    private GameType(int setsCount) {
        this.setsCount = setsCount;
    }

    public int setsCount() {
        return setsCount;
    }
}
