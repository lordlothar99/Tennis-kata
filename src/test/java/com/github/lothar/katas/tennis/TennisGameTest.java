package com.github.lothar.katas.tennis;

import static com.github.lothar.katas.tennis.Score.ADVANTAGE;
import static com.github.lothar.katas.tennis.Score.FIFTEEN;
import static com.github.lothar.katas.tennis.Score.FOURTY;
import static com.github.lothar.katas.tennis.Score.THIRTY;
import static com.github.lothar.katas.tennis.Score.ZERO;
import static java.util.stream.IntStream.range;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class TennisGameTest {

    private String player1 = "John McEnroe";
    private String player2 = "Ivan Lendl";
    private TennisGame tennisGame = new TennisGame(player1, player2);

    @Test
    public void should_both_players_have_0_points_when_game_starts() {
        assertThat(tennisGame.getScore(player1)).isEqualTo(ZERO);
        assertThat(tennisGame.getScore(player2)).isEqualTo(ZERO);
        assertThat(tennisGame.getScoreBoard()).isEqualTo("" + //
                "| Player       | Set | Score |\n" + //
                "| John McEnroe | 0   | 0     |\n" + //
                "| Ivan Lendl   | 0   | 0     |\n");
    }

    @Test
    public void should_score_be_15_0_when_first_player_scored_one_time() {
        tennisGame.scores(player1);

        assertThat(tennisGame.getScore(player1)).isEqualTo(FIFTEEN);
        assertThat(tennisGame.getScore(player2)).isEqualTo(ZERO);
        assertThat(tennisGame.getScoreBoard()).isEqualTo("" + //
                "| Player       | Set | Score |\n" + //
                "| John McEnroe | 0   | 15    |\n" + //
                "| Ivan Lendl   | 0   | 0     |\n");
    }

    @Test
    public void should_score_be_0_15_when_second_player_scored_one_time() {
        tennisGame.scores(player2);

        assertThat(tennisGame.getScore(player1)).isEqualTo(ZERO);
        assertThat(tennisGame.getScore(player2)).isEqualTo(FIFTEEN);
        assertThat(tennisGame.getScoreBoard()).isEqualTo("" + //
                "| Player       | Set | Score |\n" + //
                "| John McEnroe | 0   | 0     |\n" + //
                "| Ivan Lendl   | 0   | 15    |\n");
    }

    @Test
    public void should_score_be_15_15_when_both_players_scored_one_time() {
        tennisGame.scores(player1);
        tennisGame.scores(player2);

        assertThat(tennisGame.getScore(player1)).isEqualTo(FIFTEEN);
        assertThat(tennisGame.getScore(player2)).isEqualTo(FIFTEEN);
        assertThat(tennisGame.getScoreBoard()).isEqualTo("" + //
                "| Player       | Set | Score |\n" + //
                "| John McEnroe | 0   | 15    |\n" + //
                "| Ivan Lendl   | 0   | 15    |\n");
    }

    @Test
    public void should_score_be_30_0_when_first_player_scored_two_times() {
        repeat(2, () -> tennisGame.scores(player1));

        assertThat(tennisGame.getScore(player1)).isEqualTo(THIRTY);
        assertThat(tennisGame.getScore(player2)).isEqualTo(ZERO);
        assertThat(tennisGame.getScoreBoard()).isEqualTo("" + //
                "| Player       | Set | Score |\n" + //
                "| John McEnroe | 0   | 30    |\n" + //
                "| Ivan Lendl   | 0   | 0     |\n");
    }

    @Test
    public void should_score_be_40_0_when_first_player_scored_three_times() {
        repeat(3, () -> tennisGame.scores(player1));

        assertThat(tennisGame.getScore(player1)).isEqualTo(FOURTY);
        assertThat(tennisGame.getScore(player2)).isEqualTo(ZERO);
        assertThat(tennisGame.getScoreBoard()).isEqualTo("" + //
                "| Player       | Set | Score |\n" + //
                "| John McEnroe | 0   | 40    |\n" + //
                "| Ivan Lendl   | 0   | 0     |\n");
    }

    @Test
    public void should_score_be_1game_to_0_and_0_0_in_2nd_game_when_first_player_scored_four_times_in_a_row() {
        repeat(4, () -> tennisGame.scores(player1));

        assertThat(tennisGame.getScore(player1)).isEqualTo(ZERO);
        assertThat(tennisGame.getScore(player2)).isEqualTo(ZERO);
        assertThat(tennisGame.getGamesWon(player1)).isEqualTo(1);
        assertThat(tennisGame.getGamesWon(player2)).isEqualTo(0);
        assertThat(tennisGame.getScoreBoard()).isEqualTo("" + //
                "| Player       | Set | Score |\n" + //
                "| John McEnroe | 1   | 0     |\n" + //
                "| Ivan Lendl   | 0   | 0     |\n");
    }

    @Test
    public void should_player1_have_an_advantage_when_scores_is_deuce_and_player1_scores() {
        repeat(3, () -> tennisGame.scores(player1));
        repeat(3, () -> tennisGame.scores(player2));
        tennisGame.scores(player1);

        assertThat(tennisGame.getScore(player1)).isEqualTo(ADVANTAGE);
        assertThat(tennisGame.getScore(player2)).isEqualTo(FOURTY);
        assertThat(tennisGame.getGamesWon(player1)).isEqualTo(0);
        assertThat(tennisGame.getGamesWon(player2)).isEqualTo(0);
        assertThat(tennisGame.getScoreBoard()).isEqualTo("" + //
                "| Player       | Set | Score |\n" + //
                "| John McEnroe | 0   | ADV   |\n" + //
                "| Ivan Lendl   | 0   | 40    |\n");
    }

    @Test
    public void should_player1_win_the_game_when_he_has_an_advantage_and_he_scores() {
        repeat(3, () -> tennisGame.scores(player1));
        repeat(3, () -> tennisGame.scores(player2));
        tennisGame.scores(player1);
        tennisGame.scores(player1);

        assertThat(tennisGame.getScore(player1)).isEqualTo(ZERO);
        assertThat(tennisGame.getScore(player2)).isEqualTo(ZERO);
        assertThat(tennisGame.getGamesWon(player1)).isEqualTo(1);
        assertThat(tennisGame.getGamesWon(player2)).isEqualTo(0);
        assertThat(tennisGame.getScoreBoard()).isEqualTo("" + //
                "| Player       | Set | Score |\n" + //
                "| John McEnroe | 1   | 0     |\n" + //
                "| Ivan Lendl   | 0   | 0     |\n");
    }

    @Test
    public void should_players_be_at_deuce_when_player1_had_advantage_but_player2_scores() {
        repeat(3, () -> tennisGame.scores(player1));
        repeat(3, () -> tennisGame.scores(player2));
        tennisGame.scores(player1);
        tennisGame.scores(player2);

        assertThat(tennisGame.getScore(player1)).isEqualTo(FOURTY);
        assertThat(tennisGame.getScore(player2)).isEqualTo(FOURTY);
        assertThat(tennisGame.getGamesWon(player1)).isEqualTo(0);
        assertThat(tennisGame.getGamesWon(player2)).isEqualTo(0);
        assertThat(tennisGame.getScoreBoard()).isEqualTo("" + //
                "| Player       | Set | Score |\n" + //
                "| John McEnroe | 0   | 40    |\n" + //
                "| Ivan Lendl   | 0   | 40    |\n");
    }

    private void repeat(int times, Runnable runnable) {
        range(0, times).forEach(i -> runnable.run());
    }
}
