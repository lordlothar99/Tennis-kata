package com.github.lothar.katas.tennis.score;

public enum NormalScore implements Score<NormalScore> {
    ZERO("0"), //
    FIFTEEN("15"), //
    THIRTY("30"), //
    FOURTY("40"), //
    ADVANTAGE("ADV");

    private String value;

    private NormalScore(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public NormalScore next() {
        return NormalScore.values()[ordinal() + 1];
    }
}
