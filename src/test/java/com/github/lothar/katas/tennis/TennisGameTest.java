package com.github.lothar.katas.tennis;

import static com.github.lothar.katas.tennis.GameType.THREE_SETS;
import static com.github.lothar.katas.tennis.GameType.TWO_SETS;
import static com.github.lothar.katas.tennis.score.NormalScore.ADVANTAGE;
import static com.github.lothar.katas.tennis.score.NormalScore.FIFTEEN;
import static com.github.lothar.katas.tennis.score.NormalScore.FOURTY;
import static com.github.lothar.katas.tennis.score.NormalScore.THIRTY;
import static com.github.lothar.katas.tennis.score.NormalScore.ZERO;
import static java.util.stream.IntStream.range;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.github.lothar.katas.tennis.exception.MatchIsOverException;
import com.github.lothar.katas.tennis.score.TieBreakScore;

public abstract class TennisGameTest {

    protected String johnMcEnroe = "John McEnroe";
    protected String ivanLendl = "Ivan Lendl";
    protected TennisGame tennisGame;

    public static class MatchInTwoSets extends TennisGameTest {
        public MatchInTwoSets() {
            tennisGame = new TennisGame(johnMcEnroe, ivanLendl, TWO_SETS);
        }

        @Test
        public void should_both_players_have_0_points_when_game_starts() {
            assertThat(tennisGame.getScore(johnMcEnroe)).isEqualTo(ZERO);
            assertThat(tennisGame.getScore(ivanLendl)).isEqualTo(ZERO);
            assertThat(tennisGame.toString()).isEqualTo("" + //
                    "| Player       | Set 1 | Score |\n" + //
                    "| John McEnroe | 0     | 0     |\n" + //
                    "| Ivan Lendl   | 0     | 0     |\n");
        }

        @Test
        public void should_score_be_15_0_when_first_player_scored_one_time() {
            tennisGame.scores(johnMcEnroe);

            assertThat(tennisGame.getScore(johnMcEnroe)).isEqualTo(FIFTEEN);
            assertThat(tennisGame.getScore(ivanLendl)).isEqualTo(ZERO);
            assertThat(tennisGame.toString()).isEqualTo("" + //
                    "| Player       | Set 1 | Score |\n" + //
                    "| John McEnroe | 0     | 15    |\n" + //
                    "| Ivan Lendl   | 0     | 0     |\n");
        }

        @Test
        public void should_score_be_0_15_when_second_player_scored_one_time() {
            tennisGame.scores(ivanLendl);

            assertThat(tennisGame.getScore(johnMcEnroe)).isEqualTo(ZERO);
            assertThat(tennisGame.getScore(ivanLendl)).isEqualTo(FIFTEEN);
            assertThat(tennisGame.toString()).isEqualTo("" + //
                    "| Player       | Set 1 | Score |\n" + //
                    "| John McEnroe | 0     | 0     |\n" + //
                    "| Ivan Lendl   | 0     | 15    |\n");
        }

        @Test
        public void should_score_be_15_15_when_both_players_scored_one_time() {
            tennisGame.scores(johnMcEnroe);
            tennisGame.scores(ivanLendl);

            assertThat(tennisGame.getScore(johnMcEnroe)).isEqualTo(FIFTEEN);
            assertThat(tennisGame.getScore(ivanLendl)).isEqualTo(FIFTEEN);
            assertThat(tennisGame.toString()).isEqualTo("" + //
                    "| Player       | Set 1 | Score |\n" + //
                    "| John McEnroe | 0     | 15    |\n" + //
                    "| Ivan Lendl   | 0     | 15    |\n");
        }

        @Test
        public void should_score_be_30_0_when_first_player_scored_two_times() {
            repeat(2, () -> tennisGame.scores(johnMcEnroe));

            assertThat(tennisGame.getScore(johnMcEnroe)).isEqualTo(THIRTY);
            assertThat(tennisGame.getScore(ivanLendl)).isEqualTo(ZERO);
            assertThat(tennisGame.toString()).isEqualTo("" + //
                    "| Player       | Set 1 | Score |\n" + //
                    "| John McEnroe | 0     | 30    |\n" + //
                    "| Ivan Lendl   | 0     | 0     |\n");
        }

        @Test
        public void should_score_be_40_0_when_first_player_scored_three_times() {
            repeat(3, () -> tennisGame.scores(johnMcEnroe));

            assertThat(tennisGame.getScore(johnMcEnroe)).isEqualTo(FOURTY);
            assertThat(tennisGame.getScore(ivanLendl)).isEqualTo(ZERO);
            assertThat(tennisGame.toString()).isEqualTo("" + //
                    "| Player       | Set 1 | Score |\n" + //
                    "| John McEnroe | 0     | 40    |\n" + //
                    "| Ivan Lendl   | 0     | 0     |\n");
        }

        @Test
        public void should_score_be_1game_to_0_and_0_0_in_2nd_game_when_first_player_scored_four_times_in_a_row() {
            repeat(4, () -> tennisGame.scores(johnMcEnroe));

            assertThat(tennisGame.getScore(johnMcEnroe)).isEqualTo(ZERO);
            assertThat(tennisGame.getScore(ivanLendl)).isEqualTo(ZERO);
            assertThat(tennisGame.getGamesWon(johnMcEnroe)).isEqualTo(1);
            assertThat(tennisGame.getGamesWon(ivanLendl)).isEqualTo(0);
            assertThat(tennisGame.toString()).isEqualTo("" + //
                    "| Player       | Set 1 | Score |\n" + //
                    "| John McEnroe | 1     | 0     |\n" + //
                    "| Ivan Lendl   | 0     | 0     |\n");
        }

        @Test
        public void should_McEnroe_have_an_advantage_when_scores_is_deuce_and_McEnroe_scores() {
            repeat(3, () -> tennisGame.scores(johnMcEnroe));
            repeat(3, () -> tennisGame.scores(ivanLendl));
            tennisGame.scores(johnMcEnroe);

            assertThat(tennisGame.getScore(johnMcEnroe)).isEqualTo(ADVANTAGE);
            assertThat(tennisGame.getScore(ivanLendl)).isEqualTo(FOURTY);
            assertThat(tennisGame.getGamesWon(johnMcEnroe)).isEqualTo(0);
            assertThat(tennisGame.getGamesWon(ivanLendl)).isEqualTo(0);
            assertThat(tennisGame.toString()).isEqualTo("" + //
                    "| Player       | Set 1 | Score |\n" + //
                    "| John McEnroe | 0     | ADV   |\n" + //
                    "| Ivan Lendl   | 0     | 40    |\n");
        }

        @Test
        public void should_McEnroe_win_the_game_when_he_has_an_advantage_and_he_scores() {
            tennisGame.getPlayer(johnMcEnroe).setScore(ADVANTAGE);
            tennisGame.getPlayer(ivanLendl).setScore(FOURTY);

            tennisGame.scores(johnMcEnroe);

            assertThat(tennisGame.getScore(johnMcEnroe)).isEqualTo(ZERO);
            assertThat(tennisGame.getScore(ivanLendl)).isEqualTo(ZERO);
            assertThat(tennisGame.getGamesWon(johnMcEnroe)).isEqualTo(1);
            assertThat(tennisGame.getGamesWon(ivanLendl)).isEqualTo(0);
            assertThat(tennisGame.toString()).isEqualTo("" + //
                    "| Player       | Set 1 | Score |\n" + //
                    "| John McEnroe | 1     | 0     |\n" + //
                    "| Ivan Lendl   | 0     | 0     |\n");
        }

        @Test
        public void should_players_be_at_deuce_when_McEnroe_had_advantage_but_Lendl_scores() {
            tennisGame.getPlayer(johnMcEnroe).setScore(ADVANTAGE);
            tennisGame.getPlayer(ivanLendl).setScore(FOURTY);

            tennisGame.scores(ivanLendl);

            assertThat(tennisGame.getScore(johnMcEnroe)).isEqualTo(FOURTY);
            assertThat(tennisGame.getScore(ivanLendl)).isEqualTo(FOURTY);
            assertThat(tennisGame.getGamesWon(johnMcEnroe)).isEqualTo(0);
            assertThat(tennisGame.getGamesWon(ivanLendl)).isEqualTo(0);
            assertThat(tennisGame.toString()).isEqualTo("" + //
                    "| Player       | Set 1 | Score |\n" + //
                    "| John McEnroe | 0     | 40    |\n" + //
                    "| Ivan Lendl   | 0     | 40    |\n");
        }

        @Test
        public void should_McEnroe_win_the_match_when_he_scores_in_last_game_of_the_set() {
            setWonBy(johnMcEnroe, 6, 0);
            tennisGame.getPlayer(johnMcEnroe).setGamesWon(5);
            tennisGame.getPlayer(ivanLendl).setGamesWon(0);
            tennisGame.getPlayer(johnMcEnroe).setScore(ADVANTAGE);
            tennisGame.getPlayer(ivanLendl).setScore(ZERO);

            tennisGame.scores(johnMcEnroe);

            assertThat(tennisGame.getScore(johnMcEnroe)).isEqualTo(ZERO);
            assertThat(tennisGame.getScore(ivanLendl)).isEqualTo(ZERO);
            assertThat(tennisGame.getGamesWon(johnMcEnroe)).isEqualTo(6);
            assertThat(tennisGame.getGamesWon(ivanLendl)).isEqualTo(0);
            assertThat(tennisGame.getGamesWonInSet(johnMcEnroe, 1)).isEqualTo(6);
            assertThat(tennisGame.getGamesWonInSet(ivanLendl, 1)).isEqualTo(0);
            assertThat(tennisGame.getSetsWon(johnMcEnroe)).isEqualTo(2);
            assertThat(tennisGame.getSetsWon(ivanLendl)).isEqualTo(0);
            assertThat(tennisGame.getWinnerName()).isEqualTo(johnMcEnroe);
            assertThat(tennisGame.isMatchOver()).isTrue();
            assertThat(tennisGame.toString()).isEqualTo("" + //
                    "| Player       | Set 1 | Set 2 | Result |\n" + //
                    "| John McEnroe | 6     | 6     | WINNER |\n" + //
                    "| Ivan Lendl   | 0     | 0     |        |\n");
        }

        @Test(expected = MatchIsOverException.class)
        public void should_referee_protest_when_players_still_play_but_match_is_over() {
            setWonBy(johnMcEnroe, 6, 0);
            tennisGame.getPlayer(johnMcEnroe).setGamesWon(5);
            tennisGame.getPlayer(ivanLendl).setGamesWon(0);
            tennisGame.getPlayer(johnMcEnroe).setScore(ADVANTAGE);
            tennisGame.getPlayer(ivanLendl).setScore(ZERO);
            tennisGame.scores(johnMcEnroe);

            tennisGame.scores(johnMcEnroe);
        }

        @Test
        public void should_McEnroe_win_the_match_when_he_won_2_sets_and_Lendl_only_1() {
            setWonBy(johnMcEnroe, 6, 0);
            setWonBy(ivanLendl, 0, 6);
            tennisGame.getPlayer(johnMcEnroe).setGamesWon(5);
            tennisGame.getPlayer(ivanLendl).setGamesWon(0);
            tennisGame.getPlayer(johnMcEnroe).setScore(ADVANTAGE);
            tennisGame.getPlayer(ivanLendl).setScore(ZERO);

            tennisGame.scores(johnMcEnroe);

            assertThat(tennisGame.getScore(johnMcEnroe)).isEqualTo(ZERO);
            assertThat(tennisGame.getScore(ivanLendl)).isEqualTo(ZERO);
            assertThat(tennisGame.getGamesWon(johnMcEnroe)).isEqualTo(6);
            assertThat(tennisGame.getGamesWon(ivanLendl)).isEqualTo(0);
            assertThat(tennisGame.getGamesWonInSet(johnMcEnroe, 1)).isEqualTo(6);
            assertThat(tennisGame.getGamesWonInSet(ivanLendl, 1)).isEqualTo(0);
            assertThat(tennisGame.getSetsWon(johnMcEnroe)).isEqualTo(2);
            assertThat(tennisGame.getSetsWon(ivanLendl)).isEqualTo(1);
            assertThat(tennisGame.getWinnerName()).isEqualTo(johnMcEnroe);
            assertThat(tennisGame.isMatchOver()).isTrue();
            assertThat(tennisGame.toString()).isEqualTo("" + //
                    "| Player       | Set 1 | Set 2 | Set 3 | Result |\n" + //
                    "| John McEnroe | 6     | 0     | 6     | WINNER |\n" + //
                    "| Ivan Lendl   | 0     | 6     | 0     |        |\n");
        }
    }

    public static class MatchInThreeSets extends TennisGameTest {
        public MatchInThreeSets() {
            tennisGame = new TennisGame(johnMcEnroe, ivanLendl, THREE_SETS);
        }

        @Test
        public void should_McEnroe_win_the_set_when_he_scores_in_last_game_of_the_set() {
            setWonBy(johnMcEnroe, 6, 0);

            tennisGame.scores(johnMcEnroe);

            assertThat(tennisGame.getScore(johnMcEnroe)).isEqualTo(FIFTEEN);
            assertThat(tennisGame.getScore(ivanLendl)).isEqualTo(ZERO);
            assertThat(tennisGame.getGamesWon(johnMcEnroe)).isEqualTo(0);
            assertThat(tennisGame.getGamesWon(ivanLendl)).isEqualTo(0);
            assertThat(tennisGame.getGamesWonInSet(johnMcEnroe, 1)).isEqualTo(6);
            assertThat(tennisGame.getGamesWonInSet(ivanLendl, 1)).isEqualTo(0);
            assertThat(tennisGame.getSetsWon(johnMcEnroe)).isEqualTo(1);
            assertThat(tennisGame.getSetsWon(ivanLendl)).isEqualTo(0);
            assertThat(tennisGame.toString()).isEqualTo("" + //
                    "| Player       | Set 1 | Set 2 | Score |\n" + //
                    "| John McEnroe | 6     | 0     | 15    |\n" + //
                    "| Ivan Lendl   | 0     | 0     | 0     |\n");
        }

        @Test
        public void should_McEnroe_win_the_match_when_he_won_3_sets_and_Lendl_none() {
            setWonBy(johnMcEnroe, 6, 0);
            setWonBy(johnMcEnroe, 6, 0);
            tennisGame.getPlayer(johnMcEnroe).setGamesWon(5);
            tennisGame.getPlayer(ivanLendl).setGamesWon(0);
            tennisGame.getPlayer(johnMcEnroe).setScore(ADVANTAGE);
            tennisGame.getPlayer(ivanLendl).setScore(ZERO);

            tennisGame.scores(johnMcEnroe);

            assertThat(tennisGame.getScore(johnMcEnroe)).isEqualTo(ZERO);
            assertThat(tennisGame.getScore(ivanLendl)).isEqualTo(ZERO);
            assertThat(tennisGame.getGamesWon(johnMcEnroe)).isEqualTo(6);
            assertThat(tennisGame.getGamesWon(ivanLendl)).isEqualTo(0);
            assertThat(tennisGame.getGamesWonInSet(johnMcEnroe, 1)).isEqualTo(6);
            assertThat(tennisGame.getGamesWonInSet(ivanLendl, 1)).isEqualTo(0);
            assertThat(tennisGame.getSetsWon(johnMcEnroe)).isEqualTo(3);
            assertThat(tennisGame.getSetsWon(ivanLendl)).isEqualTo(0);
            assertThat(tennisGame.getWinnerName()).isEqualTo(johnMcEnroe);
            assertThat(tennisGame.isMatchOver()).isTrue();
            assertThat(tennisGame.toString()).isEqualTo("" + //
                    "| Player       | Set 1 | Set 2 | Set 3 | Result |\n" + //
                    "| John McEnroe | 6     | 6     | 6     | WINNER |\n" + //
                    "| Ivan Lendl   | 0     | 0     | 0     |        |\n");
        }

        @Test(expected = MatchIsOverException.class)
        public void should_referee_protest_when_players_still_play_but_match_is_over() {
            setWonBy(johnMcEnroe, 6, 0);
            setWonBy(johnMcEnroe, 6, 0);
            tennisGame.getPlayer(johnMcEnroe).setGamesWon(5);
            tennisGame.getPlayer(ivanLendl).setGamesWon(0);
            tennisGame.getPlayer(johnMcEnroe).setScore(ADVANTAGE);
            tennisGame.getPlayer(ivanLendl).setScore(ZERO);
            tennisGame.scores(johnMcEnroe);

            tennisGame.scores(johnMcEnroe);
        }

        @Test
        public void should_match_continue_when_McEnro_won_2_sets_and_Lendl_1() {
            setWonBy(johnMcEnroe, 6, 0);
            setWonBy(ivanLendl, 0, 6);
            tennisGame.getPlayer(johnMcEnroe).setGamesWon(5);
            tennisGame.getPlayer(ivanLendl).setGamesWon(0);
            tennisGame.getPlayer(johnMcEnroe).setScore(ADVANTAGE);
            tennisGame.getPlayer(ivanLendl).setScore(ZERO);
            tennisGame.scores(johnMcEnroe);

            tennisGame.scores(johnMcEnroe);

            assertThat(tennisGame.getScore(johnMcEnroe)).isEqualTo(FIFTEEN);
            assertThat(tennisGame.getScore(ivanLendl)).isEqualTo(ZERO);
            assertThat(tennisGame.getGamesWon(johnMcEnroe)).isEqualTo(0);
            assertThat(tennisGame.getGamesWon(ivanLendl)).isEqualTo(0);
            assertThat(tennisGame.getGamesWonInSet(johnMcEnroe, 1)).isEqualTo(6);
            assertThat(tennisGame.getGamesWonInSet(ivanLendl, 1)).isEqualTo(0);
            assertThat(tennisGame.getGamesWonInSet(johnMcEnroe, 2)).isEqualTo(0);
            assertThat(tennisGame.getGamesWonInSet(ivanLendl, 2)).isEqualTo(6);
            assertThat(tennisGame.getGamesWonInSet(johnMcEnroe, 3)).isEqualTo(6);
            assertThat(tennisGame.getGamesWonInSet(ivanLendl, 3)).isEqualTo(0);
            assertThat(tennisGame.getSetsWon(johnMcEnroe)).isEqualTo(2);
            assertThat(tennisGame.getSetsWon(ivanLendl)).isEqualTo(1);
            assertThat(tennisGame.isMatchOver()).isFalse();
            assertThat(tennisGame.toString()).isEqualTo("" + //
                    "| Player       | Set 1 | Set 2 | Set 3 | Set 4 | Score |\n" + //
                    "| John McEnroe | 6     | 0     | 6     | 0     | 15    |\n" + //
                    "| Ivan Lendl   | 0     | 6     | 0     | 0     | 0     |\n");
        }

        @Test
        public void should_match_continue_when_McEnro_won_2_sets_and_Lendl_2() {
            setWonBy(johnMcEnroe, 6, 0);
            setWonBy(ivanLendl, 0, 6);
            setWonBy(ivanLendl, 0, 6);
            tennisGame.getPlayer(johnMcEnroe).setGamesWon(5);
            tennisGame.getPlayer(ivanLendl).setGamesWon(0);
            tennisGame.getPlayer(johnMcEnroe).setScore(ADVANTAGE);
            tennisGame.getPlayer(ivanLendl).setScore(ZERO);
            tennisGame.scores(johnMcEnroe);

            tennisGame.scores(johnMcEnroe);

            assertThat(tennisGame.getScore(johnMcEnroe)).isEqualTo(FIFTEEN);
            assertThat(tennisGame.getScore(ivanLendl)).isEqualTo(ZERO);
            assertThat(tennisGame.getGamesWon(johnMcEnroe)).isEqualTo(0);
            assertThat(tennisGame.getGamesWon(ivanLendl)).isEqualTo(0);
            assertThat(tennisGame.getGamesWonInSet(johnMcEnroe, 1)).isEqualTo(6);
            assertThat(tennisGame.getGamesWonInSet(ivanLendl, 1)).isEqualTo(0);
            assertThat(tennisGame.getGamesWonInSet(johnMcEnroe, 2)).isEqualTo(0);
            assertThat(tennisGame.getGamesWonInSet(ivanLendl, 2)).isEqualTo(6);
            assertThat(tennisGame.getGamesWonInSet(johnMcEnroe, 3)).isEqualTo(0);
            assertThat(tennisGame.getGamesWonInSet(ivanLendl, 3)).isEqualTo(6);
            assertThat(tennisGame.getGamesWonInSet(johnMcEnroe, 4)).isEqualTo(6);
            assertThat(tennisGame.getGamesWonInSet(ivanLendl, 4)).isEqualTo(0);
            assertThat(tennisGame.getSetsWon(johnMcEnroe)).isEqualTo(2);
            assertThat(tennisGame.getSetsWon(ivanLendl)).isEqualTo(2);
            assertThat(tennisGame.isMatchOver()).isFalse();
            assertThat(tennisGame.toString()).isEqualTo("" + //
                    "| Player       | Set 1 | Set 2 | Set 3 | Set 4 | Set 5 | Score |\n" + //
                    "| John McEnroe | 6     | 0     | 0     | 6     | 0     | 15    |\n" + //
                    "| Ivan Lendl   | 0     | 6     | 6     | 0     | 0     | 0     |\n");
        }
    }

    public static class TwoGamesOfDifference extends TennisGameTest {
        public TwoGamesOfDifference() {
            tennisGame = new TennisGame(johnMcEnroe, ivanLendl, THREE_SETS);
        }

        @Test
        public void should_set_still_continue_when_not_two_games_of_difference() {
            tennisGame.getPlayer(johnMcEnroe).setGamesWon(5);
            tennisGame.getPlayer(ivanLendl).setGamesWon(5);
            tennisGame.getPlayer(johnMcEnroe).setScore(ADVANTAGE);
            tennisGame.getPlayer(ivanLendl).setScore(ZERO);
            tennisGame.scores(johnMcEnroe);

            tennisGame.scores(johnMcEnroe);

            assertThat(tennisGame.getScore(johnMcEnroe)).isEqualTo(FIFTEEN);
            assertThat(tennisGame.getScore(ivanLendl)).isEqualTo(ZERO);
            assertThat(tennisGame.getGamesWon(johnMcEnroe)).isEqualTo(6);
            assertThat(tennisGame.getGamesWon(ivanLendl)).isEqualTo(5);
            assertThat(tennisGame.getSetsWon(johnMcEnroe)).isEqualTo(0);
            assertThat(tennisGame.getSetsWon(ivanLendl)).isEqualTo(0);
            assertThat(tennisGame.getWinnerName()).isNull();
            assertThat(tennisGame.toString()).isEqualTo("" + //
                    "| Player       | Set 1 | Score |\n" + //
                    "| John McEnroe | 6     | 15    |\n" + //
                    "| Ivan Lendl   | 5     | 0     |\n");
        }

        @Test
        public void should_set_be_won_when_two_games_of_difference() {
            tennisGame.getPlayer(johnMcEnroe).setGamesWon(8);
            tennisGame.getPlayer(ivanLendl).setGamesWon(7);
            tennisGame.getPlayer(johnMcEnroe).setScore(ADVANTAGE);
            tennisGame.getPlayer(ivanLendl).setScore(ZERO);

            tennisGame.scores(johnMcEnroe);

            assertThat(tennisGame.getScore(johnMcEnroe)).isEqualTo(ZERO);
            assertThat(tennisGame.getScore(ivanLendl)).isEqualTo(ZERO);
            assertThat(tennisGame.getGamesWon(johnMcEnroe)).isEqualTo(0);
            assertThat(tennisGame.getGamesWon(ivanLendl)).isEqualTo(0);
            assertThat(tennisGame.getSetsWon(johnMcEnroe)).isEqualTo(1);
            assertThat(tennisGame.getSetsWon(ivanLendl)).isEqualTo(0);
            assertThat(tennisGame.toString()).isEqualTo("" + //
                    "| Player       | Set 1 | Set 2 | Score |\n" + //
                    "| John McEnroe | 9     | 0     | 0     |\n" + //
                    "| Ivan Lendl   | 7     | 0     | 0     |\n");
        }
    }

    public static class TieBreak extends TennisGameTest {
        public TieBreak() {
            tennisGame = new TennisGame(johnMcEnroe, ivanLendl, TWO_SETS);
        }

        @Test
        public void should_there_be_a_tie_break_when_both_players_have_6_games_in_last_set() {
            tennisGame.getPlayer(johnMcEnroe).setGamesWon(5);
            tennisGame.getPlayer(ivanLendl).setGamesWon(6);
            tennisGame.getPlayer(johnMcEnroe).setScore(ADVANTAGE);
            tennisGame.getPlayer(ivanLendl).setScore(ZERO);

            tennisGame.scores(johnMcEnroe);

            assertThat(tennisGame.getScore(johnMcEnroe)).isEqualTo(TieBreakScore.ZERO);
            assertThat(tennisGame.getScore(ivanLendl)).isEqualTo(TieBreakScore.ZERO);
            assertThat(tennisGame.getGamesWon(johnMcEnroe)).isEqualTo(6);
            assertThat(tennisGame.getGamesWon(ivanLendl)).isEqualTo(6);
            assertThat(tennisGame.getSetsWon(johnMcEnroe)).isEqualTo(0);
            assertThat(tennisGame.getSetsWon(ivanLendl)).isEqualTo(0);
            assertThat(tennisGame.isTieBreak()).isTrue();
        }

        @Test
        public void should_points_be_classic_ordinal_when_there_is_a_tie_break() {
            setWonBy(johnMcEnroe, 6, 4);

            tennisGame.getPlayer(johnMcEnroe).setGamesWon(6);
            tennisGame.getPlayer(ivanLendl).setGamesWon(6);
            tennisGame.getPlayer(johnMcEnroe).setScore(TieBreakScore.ZERO);
            tennisGame.getPlayer(ivanLendl).setScore(TieBreakScore.ZERO);

            tennisGame.scores(johnMcEnroe);

            assertThat(tennisGame.getScore(johnMcEnroe)).isEqualTo(new TieBreakScore(1));
            assertThat(tennisGame.toString()).isEqualTo("" + //
                    "| Player       | Set 1 | Set 2 | Score |\n" + //
                    "| John McEnroe | 6     | 6     | 1     |\n" + //
                    "| Ivan Lendl   | 4     | 6     | 0     |\n");
        }

        @Test
        public void should_match_be_won_when_one_player_wins_7_points_in_tie_break() {
            setWonBy(johnMcEnroe, 6, 4);

            tennisGame.getPlayer(johnMcEnroe).setGamesWon(6);
            tennisGame.getPlayer(ivanLendl).setGamesWon(6);
            tennisGame.getPlayer(johnMcEnroe).setScore(new TieBreakScore(6));
            tennisGame.getPlayer(ivanLendl).setScore(TieBreakScore.ZERO);

            tennisGame.scores(johnMcEnroe);

            assertThat(tennisGame.isMatchOver()).isTrue();
            assertThat(tennisGame.getWinnerName()).isEqualTo(johnMcEnroe);
            assertThat(tennisGame.toString()).isEqualTo("" + //
                    "| Player       | Set 1 | Set 2 | Result |\n" + //
                    "| John McEnroe | 6     | 7     | WINNER |\n" + //
                    "| Ivan Lendl   | 4     | 6     |        |\n");
        }

        @Test
        public void should_match_still_continue_after_7_when_there_are_less_than_2_points_of_difference_in_tie_break() {
            setWonBy(johnMcEnroe, 6, 4);

            tennisGame.getPlayer(johnMcEnroe).setGamesWon(6);
            tennisGame.getPlayer(ivanLendl).setGamesWon(6);
            tennisGame.getPlayer(johnMcEnroe).setScore(new TieBreakScore(6));
            tennisGame.getPlayer(ivanLendl).setScore(new TieBreakScore(6));

            tennisGame.scores(johnMcEnroe);

            assertThat(tennisGame.isMatchOver()).isFalse();
            assertThat(tennisGame.toString()).isEqualTo("" + //
                    "| Player       | Set 1 | Set 2 | Score |\n" + //
                    "| John McEnroe | 6     | 6     | 7     |\n" + //
                    "| Ivan Lendl   | 4     | 6     | 6     |\n");
        }

        @Test
        public void should_match_be_won_when_there_are_at_least_2_points_of_difference_above_7_in_tie_break() {
            setWonBy(johnMcEnroe, 6, 4);

            tennisGame.getPlayer(johnMcEnroe).setGamesWon(6);
            tennisGame.getPlayer(ivanLendl).setGamesWon(6);
            tennisGame.getPlayer(johnMcEnroe).setScore(new TieBreakScore(9));
            tennisGame.getPlayer(ivanLendl).setScore(new TieBreakScore(8));

            tennisGame.scores(johnMcEnroe);

            assertThat(tennisGame.isMatchOver()).isTrue();
            assertThat(tennisGame.getWinnerName()).isEqualTo(johnMcEnroe);
            assertThat(tennisGame.toString()).isEqualTo("" + //
                    "| Player       | Set 1 | Set 2 | Result |\n" + //
                    "| John McEnroe | 6     | 7     | WINNER |\n" + //
                    "| Ivan Lendl   | 4     | 6     |        |\n");
        }
    }

    protected void setWonBy(String player, int setsJohnMcEnroe, int setsIvanLendl) {
        tennisGame.getPlayer(johnMcEnroe).setGamesWon(setsJohnMcEnroe);
        tennisGame.getPlayer(ivanLendl).setGamesWon(setsIvanLendl);
        tennisGame.getPlayer(player).incrementSetWon();
        tennisGame.getPlayer(johnMcEnroe).setupNewSet();
        tennisGame.getPlayer(ivanLendl).setupNewSet();
    }

    protected static void repeat(int times, Runnable runnable) {
        range(0, times).forEach(i -> runnable.run());
    }
}
