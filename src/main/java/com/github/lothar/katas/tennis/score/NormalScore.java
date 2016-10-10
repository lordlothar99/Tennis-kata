package com.github.lothar.katas.tennis.score;

public enum NormalScore implements Score<NormalScore> {
    ZERO("0") {
        @Override
        public NormalScore next() {
            return FIFTEEN;
        }
    },
    FIFTEEN("15") {
        @Override
        public NormalScore next() {
            return THIRTY;
        }
    },
    THIRTY("30") {
        @Override
        public NormalScore next() {
            return FOURTY;
        }
    },
    FOURTY("40") {
        @Override
        public NormalScore next() {
            return ADVANTAGE;
        }
    },
    ADVANTAGE("ADV") {
        @Override
        public NormalScore next() {
            return null;
        }
    };

    private String value;

    private NormalScore(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
