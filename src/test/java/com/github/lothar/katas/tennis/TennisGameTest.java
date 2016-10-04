package com.github.lothar.katas.tennis;

import static com.github.lothar.katas.tennis.Score.FIFTEEN;
import static com.github.lothar.katas.tennis.Score.THIRTEEN;
import static com.github.lothar.katas.tennis.Score.ZERO;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class TennisGameTest {

    private TennisGame tennisGame = new TennisGame();

    @Test
    public void should_both_players_have_0_points_when_game_starts() {
        assertThat(tennisGame.getPlayer1Score()).isEqualTo(ZERO);
        assertThat(tennisGame.getPlayer2Score()).isEqualTo(ZERO);
    }

    @Test
    public void should_score_be_15_0_when_first_player_scored_one_time() {
        tennisGame.player1Scores();

        assertThat(tennisGame.getPlayer1Score()).isEqualTo(FIFTEEN);
        assertThat(tennisGame.getPlayer2Score()).isEqualTo(ZERO);
        assertThat(tennisGame.getScores()).isEqualTo("15-0");
    }

    @Test
    public void should_score_be_0_15_when_second_player_scored_one_time() {
        tennisGame.player2Scores();

        assertThat(tennisGame.getPlayer1Score()).isEqualTo(ZERO);
        assertThat(tennisGame.getPlayer2Score()).isEqualTo(FIFTEEN);
        assertThat(tennisGame.getScores()).isEqualTo("0-15");
    }

    @Test
    public void should_score_be_15_15_when_both_players_scored_one_time() {
        tennisGame.player1Scores();
        tennisGame.player2Scores();

        assertThat(tennisGame.getPlayer1Score()).isEqualTo(FIFTEEN);
        assertThat(tennisGame.getPlayer2Score()).isEqualTo(FIFTEEN);
        assertThat(tennisGame.getScores()).isEqualTo("15-15");
    }

    @Test
    public void should_score_be_30_0_when_first_player_scored_two_times() {
        tennisGame.player1Scores();
        tennisGame.player1Scores();

        assertThat(tennisGame.getPlayer1Score()).isEqualTo(THIRTEEN);
        assertThat(tennisGame.getPlayer2Score()).isEqualTo(ZERO);
        assertThat(tennisGame.getScores()).isEqualTo("30-0");
    }
}
