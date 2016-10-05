package com.github.lothar.katas.tennis.score;

public class TieBreakScore implements Score {

    public static final TieBreakScore ZERO = new TieBreakScore(0);

    private int value;

    public TieBreakScore(int value) {
        this.value = value;
    }

    @Override
    public Score next() {
        return new TieBreakScore(value + 1);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TieBreakScore)) {
            return false;
        }
        TieBreakScore score = (TieBreakScore) obj;
        return score.value == value;
    }
}
