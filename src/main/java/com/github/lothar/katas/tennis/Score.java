package com.github.lothar.katas.tennis;

public enum Score {
    ZERO("0") {
        @Override
        public Score next() {
            return FIFTEEN;
        }
    },
    FIFTEEN("15") {
        @Override
        public Score next() {
            return THIRTEEN;
        }
    },
    THIRTEEN("30") {
        @Override
        public Score next() {
            return FOURTEEN;
        }
    },
    FOURTEEN("40") {
        @Override
        public Score next() {
            return ADVANTAGE;
        }
    },
    ADVANTAGE("ADV") {
        @Override
        public Score next() {
            return null;
        }
    };

    private String value;

    private Score(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public abstract Score next();
}
