package com.github.lothar.katas.tennis;

public enum SetsToWin {

    TWO(2), //
    THREE(3);

    private int value;

    private SetsToWin(int value) {
        this.value = value;
    }

    public int intValue() {
        return value;
    }
}
