package com.github.lothar.katas.tennis;

public enum Score {
    ZERO("0"), //
    FIFTEEN("15");

    private String value;

    private Score(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
