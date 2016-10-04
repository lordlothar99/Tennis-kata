package com.github.lothar.katas.tennis;

import static com.github.lothar.katas.tennis.Score.FIFTEEN;
import static com.github.lothar.katas.tennis.Score.ZERO;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class TennisGameTest {

    @Test
    public void should_both_players_have_0_points_when_game_starts() {
        TennisGame tennisGame = new TennisGame();

        assertThat(tennisGame.getPlayer1Score()).isEqualTo(ZERO);
        assertThat(tennisGame.getPlayer2Score()).isEqualTo(ZERO);
    }

    @Test
    public void should_score_be_15_0_when_first_player_wins_one_point() {
        TennisGame tennisGame = new TennisGame();

        tennisGame.player1Scores();

        assertThat(tennisGame.getPlayer1Score()).isEqualTo(FIFTEEN);
        assertThat(tennisGame.getPlayer2Score()).isEqualTo(ZERO);
        assertThat(tennisGame.getScores()).isEqualTo("15-0");
    }
}
