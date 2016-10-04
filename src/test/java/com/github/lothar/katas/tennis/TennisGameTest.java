package com.github.lothar.katas.tennis;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class TennisGameTest {

    @Test
    public void should_both_players_have_0_points_when_game_starts() {
        TennisGame tennisGame = new TennisGame();

        assertThat(tennisGame.getPlayer1Score()).isEqualTo(0);
        assertThat(tennisGame.getPlayer2Score()).isEqualTo(0);
    }
}
