package com.github.lothar.katas.tennis;

import static com.github.lothar.katas.tennis.Score.ZERO;

public class Player {

    private Score score = ZERO;

    public Score getScore() {
        return score;
    }

    public void scores() {
        score = score.next();
    }
}
