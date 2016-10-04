package com.github.lothar.katas.tennis;

import java.util.Collection;

class ResultBoard extends AbstractBoard {

    private static final String RESULT_COLUMN = "Result";
    private static final String WINNER = "WINNER";
    private Player winner;

    public ResultBoard(Collection<Player> players, GameType gameType, Player winner) {
        super(players, gameType);
        this.winner = winner;
    }

    @Override
    protected String lastColumnHeader() {
        return RESULT_COLUMN;
    }

    @Override
    protected Object lastColumnValue(Player player) {
        return winner.equals(player) ? WINNER : "";
    }
}
