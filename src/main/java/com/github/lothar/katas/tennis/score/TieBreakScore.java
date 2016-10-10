package com.github.lothar.katas.tennis.score;

public class TieBreakScore implements Score<TieBreakScore> {

    public static final TieBreakScore ZERO = new TieBreakScore(0);

    private int value;

    public TieBreakScore(int value) {
        this.value = value;
    }

    @Override
    public TieBreakScore next() {
        return new TieBreakScore(value + 1);
    }

    @Override
    public int compareTo(TieBreakScore score) {
        return Integer.compare(value, ((TieBreakScore) score).value);
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
