package com.github.lothar.katas.tennis;

import static com.github.lothar.katas.tennis.Score.FIFTEEN;
import static com.github.lothar.katas.tennis.Score.FOURTEEN;
import static com.github.lothar.katas.tennis.Score.THIRTEEN;
import static com.github.lothar.katas.tennis.Score.ZERO;
import static java.util.stream.IntStream.range;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class TennisGameTest {

    private Player player1 = new Player();
    private Player player2 = new Player();
    private TennisGame tennisGame = new TennisGame(player1, player2);

    @Test
    public void should_both_players_have_0_points_when_game_starts() {
        assertThat(tennisGame.getScore(player1)).isEqualTo(ZERO);
        assertThat(tennisGame.getScore(player2)).isEqualTo(ZERO);
        assertThat(tennisGame.getScores()).isEqualTo("0-0 ; 0-0");
    }

    @Test
    public void should_score_be_15_0_when_first_player_scored_one_time() {
        tennisGame.scores(player1);

        assertThat(tennisGame.getScore(player1)).isEqualTo(FIFTEEN);
        assertThat(tennisGame.getScore(player2)).isEqualTo(ZERO);
        assertThat(tennisGame.getScores()).isEqualTo("0-0 ; 15-0");
    }

    @Test
    public void should_score_be_0_15_when_second_player_scored_one_time() {
        tennisGame.scores(player2);

        assertThat(tennisGame.getScore(player1)).isEqualTo(ZERO);
        assertThat(tennisGame.getScore(player2)).isEqualTo(FIFTEEN);
        assertThat(tennisGame.getScores()).isEqualTo("0-0 ; 0-15");
    }

    @Test
    public void should_score_be_15_15_when_both_players_scored_one_time() {
        tennisGame.scores(player1);
        tennisGame.scores(player2);

        assertThat(tennisGame.getScore(player1)).isEqualTo(FIFTEEN);
        assertThat(tennisGame.getScore(player2)).isEqualTo(FIFTEEN);
        assertThat(tennisGame.getScores()).isEqualTo("0-0 ; 15-15");
    }

    @Test
    public void should_score_be_30_0_when_first_player_scored_two_times() {
        repeat(2, () -> tennisGame.scores(player1));

        assertThat(tennisGame.getScore(player1)).isEqualTo(THIRTEEN);
        assertThat(tennisGame.getScore(player2)).isEqualTo(ZERO);
        assertThat(tennisGame.getScores()).isEqualTo("0-0 ; 30-0");
    }

    @Test
    public void should_score_be_40_0_when_first_player_scored_three_times() {
        repeat(3, () -> tennisGame.scores(player1));

        assertThat(tennisGame.getScore(player1)).isEqualTo(FOURTEEN);
        assertThat(tennisGame.getScore(player2)).isEqualTo(ZERO);
        assertThat(tennisGame.getScores()).isEqualTo("0-0 ; 40-0");
    }

    @Test
    public void should_score_be_1game_to_0_and_0_0_in_2nd_game_when_first_player_scored_four_times_in_a_row() {
        repeat(4, () -> tennisGame.scores(player1));

        assertThat(tennisGame.getScore(player1)).isEqualTo(ZERO);
        assertThat(tennisGame.getScore(player2)).isEqualTo(ZERO);
        assertThat(tennisGame.getGamesWon(player1)).isEqualTo(1);
        assertThat(tennisGame.getGamesWon(player2)).isEqualTo(0);
        assertThat(tennisGame.getScores()).isEqualTo("1-0 ; 0-0");
    }

    private void repeat(int times, Runnable runnable) {
        range(0, times).forEach(i -> runnable.run());
    }
}
