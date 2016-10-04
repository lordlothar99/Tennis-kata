package com.github.lothar.katas.tennis;

import java.util.Collection;

class ScoreBoard extends AbstractBoard {

    private static final String SCORE_COLUMN = "Score";

    public ScoreBoard(Collection<Player> players, GameType gameType) {
        super(players, gameType);
    }

    protected String lastColumnHeader() {
        return SCORE_COLUMN;
    }

    protected Score lastColumnValue(Player player) {
        return player.getScore();
    }
}
