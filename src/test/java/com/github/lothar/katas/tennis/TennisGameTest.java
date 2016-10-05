package com.github.lothar.katas.tennis;

import static com.github.lothar.katas.tennis.GameType.ONE_SET;
import static com.github.lothar.katas.tennis.GameType.THREE_SETS;
import static com.github.lothar.katas.tennis.NormalScore.ADVANTAGE;
import static com.github.lothar.katas.tennis.NormalScore.FIFTEEN;
import static com.github.lothar.katas.tennis.NormalScore.FOURTY;
import static com.github.lothar.katas.tennis.NormalScore.THIRTY;
import static com.github.lothar.katas.tennis.NormalScore.ZERO;
import static java.util.stream.IntStream.range;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public abstract class TennisGameTest {

    protected String player1 = "John McEnroe";
    protected String player2 = "Ivan Lendl";

    public static class OneSet extends TennisGameTest {
        private TennisGame tennisGame = new TennisGame(player1, player2, ONE_SET);

        @Test
        public void should_both_players_have_0_points_when_game_starts() {
            assertThat(tennisGame.getScore(player1)).isEqualTo(ZERO);
            assertThat(tennisGame.getScore(player2)).isEqualTo(ZERO);
            assertThat(tennisGame.getScoreBoard()).isEqualTo("" + //
                    "| Player       | Set 1 | Score |\n" + //
                    "| John McEnroe | 0     | 0     |\n" + //
                    "| Ivan Lendl   | 0     | 0     |\n");
        }

        @Test
        public void should_score_be_15_0_when_first_player_scored_one_time() {
            tennisGame.scores(player1);

            assertThat(tennisGame.getScore(player1)).isEqualTo(FIFTEEN);
            assertThat(tennisGame.getScore(player2)).isEqualTo(ZERO);
            assertThat(tennisGame.getScoreBoard()).isEqualTo("" + //
                    "| Player       | Set 1 | Score |\n" + //
                    "| John McEnroe | 0     | 15    |\n" + //
                    "| Ivan Lendl   | 0     | 0     |\n");
        }

        @Test
        public void should_score_be_0_15_when_second_player_scored_one_time() {
            tennisGame.scores(player2);

            assertThat(tennisGame.getScore(player1)).isEqualTo(ZERO);
            assertThat(tennisGame.getScore(player2)).isEqualTo(FIFTEEN);
            assertThat(tennisGame.getScoreBoard()).isEqualTo("" + //
                    "| Player       | Set 1 | Score |\n" + //
                    "| John McEnroe | 0     | 0     |\n" + //
                    "| Ivan Lendl   | 0     | 15    |\n");
        }

        @Test
        public void should_score_be_15_15_when_both_players_scored_one_time() {
            tennisGame.scores(player1);
            tennisGame.scores(player2);

            assertThat(tennisGame.getScore(player1)).isEqualTo(FIFTEEN);
            assertThat(tennisGame.getScore(player2)).isEqualTo(FIFTEEN);
            assertThat(tennisGame.getScoreBoard()).isEqualTo("" + //
                    "| Player       | Set 1 | Score |\n" + //
                    "| John McEnroe | 0     | 15    |\n" + //
                    "| Ivan Lendl   | 0     | 15    |\n");
        }

        @Test
        public void should_score_be_30_0_when_first_player_scored_two_times() {
            repeat(2, () -> tennisGame.scores(player1));

            assertThat(tennisGame.getScore(player1)).isEqualTo(THIRTY);
            assertThat(tennisGame.getScore(player2)).isEqualTo(ZERO);
            assertThat(tennisGame.getScoreBoard()).isEqualTo("" + //
                    "| Player       | Set 1 | Score |\n" + //
                    "| John McEnroe | 0     | 30    |\n" + //
                    "| Ivan Lendl   | 0     | 0     |\n");
        }

        @Test
        public void should_score_be_40_0_when_first_player_scored_three_times() {
            repeat(3, () -> tennisGame.scores(player1));

            assertThat(tennisGame.getScore(player1)).isEqualTo(FOURTY);
            assertThat(tennisGame.getScore(player2)).isEqualTo(ZERO);
            assertThat(tennisGame.getScoreBoard()).isEqualTo("" + //
                    "| Player       | Set 1 | Score |\n" + //
                    "| John McEnroe | 0     | 40    |\n" + //
                    "| Ivan Lendl   | 0     | 0     |\n");
        }

        @Test
        public void should_score_be_1game_to_0_and_0_0_in_2nd_game_when_first_player_scored_four_times_in_a_row() {
            repeat(4, () -> tennisGame.scores(player1));

            assertThat(tennisGame.getScore(player1)).isEqualTo(ZERO);
            assertThat(tennisGame.getScore(player2)).isEqualTo(ZERO);
            assertThat(tennisGame.getGamesWon(player1)).isEqualTo(1);
            assertThat(tennisGame.getGamesWon(player2)).isEqualTo(0);
            assertThat(tennisGame.getScoreBoard()).isEqualTo("" + //
                    "| Player       | Set 1 | Score |\n" + //
                    "| John McEnroe | 1     | 0     |\n" + //
                    "| Ivan Lendl   | 0     | 0     |\n");
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
                    "| Player       | Set 1 | Score |\n" + //
                    "| John McEnroe | 0     | ADV   |\n" + //
                    "| Ivan Lendl   | 0     | 40    |\n");
        }

        @Test
        public void should_player1_win_the_game_when_he_has_an_advantage_and_he_scores() {
            tennisGame.getPlayer(player1).setScore(ADVANTAGE);
            tennisGame.getPlayer(player2).setScore(FOURTY);
            tennisGame.scores(player1);

            assertThat(tennisGame.getScore(player1)).isEqualTo(ZERO);
            assertThat(tennisGame.getScore(player2)).isEqualTo(ZERO);
            assertThat(tennisGame.getGamesWon(player1)).isEqualTo(1);
            assertThat(tennisGame.getGamesWon(player2)).isEqualTo(0);
            assertThat(tennisGame.getScoreBoard()).isEqualTo("" + //
                    "| Player       | Set 1 | Score |\n" + //
                    "| John McEnroe | 1     | 0     |\n" + //
                    "| Ivan Lendl   | 0     | 0     |\n");
        }

        @Test
        public void should_players_be_at_deuce_when_player1_had_advantage_but_player2_scores() {
            tennisGame.getPlayer(player1).setScore(ADVANTAGE);
            tennisGame.getPlayer(player2).setScore(FOURTY);
            tennisGame.scores(player2);

            assertThat(tennisGame.getScore(player1)).isEqualTo(FOURTY);
            assertThat(tennisGame.getScore(player2)).isEqualTo(FOURTY);
            assertThat(tennisGame.getGamesWon(player1)).isEqualTo(0);
            assertThat(tennisGame.getGamesWon(player2)).isEqualTo(0);
            assertThat(tennisGame.getScoreBoard()).isEqualTo("" + //
                    "| Player       | Set 1 | Score |\n" + //
                    "| John McEnroe | 0     | 40    |\n" + //
                    "| Ivan Lendl   | 0     | 40    |\n");
        }

        @Test
        public void should_player1_win_the_match_when_he_scores_in_last_game_of_the_set() {
            tennisGame.getPlayer(player1).setGamesWon(5);
            tennisGame.getPlayer(player2).setGamesWon(0);
            tennisGame.getPlayer(player1).setScore(ADVANTAGE);
            tennisGame.getPlayer(player2).setScore(ZERO);
            tennisGame.scores(player1);

            assertThat(tennisGame.getScore(player1)).isEqualTo(ZERO);
            assertThat(tennisGame.getScore(player2)).isEqualTo(ZERO);
            assertThat(tennisGame.getGamesWon(player1)).isEqualTo(0);
            assertThat(tennisGame.getGamesWon(player2)).isEqualTo(0);
            assertThat(tennisGame.getGamesWonInSet(player1, 1)).isEqualTo(6);
            assertThat(tennisGame.getGamesWonInSet(player2, 1)).isEqualTo(0);
            assertThat(tennisGame.getSetsWon(player1)).isEqualTo(1);
            assertThat(tennisGame.getSetsWon(player2)).isEqualTo(0);
            assertThat(tennisGame.getWinner()).isEqualTo(player1);
            assertThat(tennisGame.getScoreBoard()).isEqualTo("" + //
                    "| Player       | Set 1 | Result |\n" + //
                    "| John McEnroe | 6     | WINNER |\n" + //
                    "| Ivan Lendl   | 0     |        |\n");
        }

        @Test(expected = MatchIsOverException.class)
        public void should_referee_protest_when_players_still_play_but_match_is_over() {
            tennisGame.getPlayer(player1).setGamesWon(5);
            tennisGame.getPlayer(player2).setGamesWon(0);
            tennisGame.getPlayer(player1).setScore(ADVANTAGE);
            tennisGame.getPlayer(player2).setScore(ZERO);
            tennisGame.scores(player1);
            tennisGame.scores(player1);
        }
    }

    public static class ThreeSets extends TennisGameTest {
        private TennisGame tennisGame = new TennisGame(player1, player2, THREE_SETS);

        @Test
        public void should_player1_win_the_set_when_he_scores_in_last_game_of_the_set() {
            tennisGame.getPlayer(player1).setGamesWon(5);
            tennisGame.getPlayer(player2).setGamesWon(0);
            tennisGame.getPlayer(player1).setScore(ADVANTAGE);
            tennisGame.getPlayer(player2).setScore(ZERO);
            tennisGame.scores(player1);
            tennisGame.scores(player1);

            assertThat(tennisGame.getScore(player1)).isEqualTo(FIFTEEN);
            assertThat(tennisGame.getScore(player2)).isEqualTo(ZERO);
            assertThat(tennisGame.getGamesWon(player1)).isEqualTo(0);
            assertThat(tennisGame.getGamesWon(player2)).isEqualTo(0);
            assertThat(tennisGame.getGamesWonInSet(player1, 1)).isEqualTo(6);
            assertThat(tennisGame.getGamesWonInSet(player2, 1)).isEqualTo(0);
            assertThat(tennisGame.getSetsWon(player1)).isEqualTo(1);
            assertThat(tennisGame.getSetsWon(player2)).isEqualTo(0);
            assertThat(tennisGame.getScoreBoard()).isEqualTo("" + //
                    "| Player       | Set 1 | Set 2 | Set 3 | Score |\n" + //
                    "| John McEnroe | 6     | 0     | 0     | 15    |\n" + //
                    "| Ivan Lendl   | 0     | 0     | 0     | 0     |\n");
        }
    }

    public static class TwoGamesOfDifference extends TennisGameTest {
        private TennisGame tennisGame = new TennisGame(player1, player2, THREE_SETS);

        @Test
        public void should_set_still_continue_when_not_two_games_of_difference() {
            tennisGame.getPlayer(player1).setGamesWon(5);
            tennisGame.getPlayer(player2).setGamesWon(5);
            tennisGame.getPlayer(player1).setScore(ADVANTAGE);
            tennisGame.getPlayer(player2).setScore(ZERO);
            tennisGame.scores(player1);
            tennisGame.scores(player1);

            assertThat(tennisGame.getScore(player1)).isEqualTo(FIFTEEN);
            assertThat(tennisGame.getScore(player2)).isEqualTo(ZERO);
            assertThat(tennisGame.getGamesWon(player1)).isEqualTo(6);
            assertThat(tennisGame.getGamesWon(player2)).isEqualTo(5);
            assertThat(tennisGame.getSetsWon(player1)).isEqualTo(0);
            assertThat(tennisGame.getSetsWon(player2)).isEqualTo(0);
            assertThat(tennisGame.getWinner()).isNull();
            assertThat(tennisGame.getScoreBoard()).isEqualTo("" + //
                    "| Player       | Set 1 | Set 2 | Set 3 | Score |\n" + //
                    "| John McEnroe | 6     | 0     | 0     | 15    |\n" + //
                    "| Ivan Lendl   | 5     | 0     | 0     | 0     |\n");
        }

        @Test
        public void should_set_be_won_when_two_games_of_difference() {
            tennisGame.getPlayer(player1).setGamesWon(8);
            tennisGame.getPlayer(player2).setGamesWon(7);
            tennisGame.getPlayer(player1).setScore(ADVANTAGE);
            tennisGame.getPlayer(player2).setScore(ZERO);
            tennisGame.scores(player1);

            assertThat(tennisGame.getScore(player1)).isEqualTo(ZERO);
            assertThat(tennisGame.getScore(player2)).isEqualTo(ZERO);
            assertThat(tennisGame.getGamesWon(player1)).isEqualTo(0);
            assertThat(tennisGame.getGamesWon(player2)).isEqualTo(0);
            assertThat(tennisGame.getSetsWon(player1)).isEqualTo(1);
            assertThat(tennisGame.getSetsWon(player2)).isEqualTo(0);
            assertThat(tennisGame.getScoreBoard()).isEqualTo("" + //
                    "| Player       | Set 1 | Set 2 | Set 3 | Score |\n" + //
                    "| John McEnroe | 9     | 0     | 0     | 0     |\n" + //
                    "| Ivan Lendl   | 7     | 0     | 0     | 0     |\n");
        }
    }

    public static class TieBreak extends TennisGameTest {
        private TennisGame tennisGame = new TennisGame(player1, player2, ONE_SET);

        @Test
        public void should_there_be_a_tie_break_when_both_players_have_6_games_in_last_set() {
            tennisGame.getPlayer(player1).setGamesWon(5);
            tennisGame.getPlayer(player2).setGamesWon(6);
            tennisGame.getPlayer(player1).setScore(ADVANTAGE);
            tennisGame.getPlayer(player2).setScore(ZERO);
            tennisGame.scores(player1);

            assertThat(tennisGame.getScore(player1)).isEqualTo(TieBreakScore.ZERO);
            assertThat(tennisGame.getScore(player2)).isEqualTo(TieBreakScore.ZERO);
            assertThat(tennisGame.getGamesWon(player1)).isEqualTo(6);
            assertThat(tennisGame.getGamesWon(player2)).isEqualTo(6);
            assertThat(tennisGame.getSetsWon(player1)).isEqualTo(0);
            assertThat(tennisGame.getSetsWon(player2)).isEqualTo(0);
            assertThat(tennisGame.isTieBreak()).isTrue();
        }

        @Test
        public void should_points_be_classic_oridnal_when_there_is_a_tie_break() {
            tennisGame.getPlayer(player1).setGamesWon(6);
            tennisGame.getPlayer(player2).setGamesWon(6);
            tennisGame.getPlayer(player1).setScore(TieBreakScore.ZERO);
            tennisGame.getPlayer(player2).setScore(TieBreakScore.ZERO);
            tennisGame.scores(player1);

            assertThat(tennisGame.getScore(player1)).isEqualTo(new TieBreakScore(1));
            assertThat(tennisGame.getScoreBoard()).isEqualTo("" + //
                    "| Player       | Set 1 | Score |\n" + //
                    "| John McEnroe | 6     | 1     |\n" + //
                    "| Ivan Lendl   | 6     | 0     |\n");
        }
    }

    protected static void repeat(int times, Runnable runnable) {
        range(0, times).forEach(i -> runnable.run());
    }
}
