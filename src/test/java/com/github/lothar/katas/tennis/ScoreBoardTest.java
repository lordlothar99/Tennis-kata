package com.github.lothar.katas.tennis;

import static com.github.lothar.katas.tennis.GameType.FIVE_SETS;
import static com.github.lothar.katas.tennis.GameType.ONE_SET;
import static com.github.lothar.katas.tennis.GameType.THREE_SETS;
import static com.github.lothar.katas.tennis.score.NormalScore.ADVANTAGE;
import static com.github.lothar.katas.tennis.score.NormalScore.FIFTEEN;
import static com.github.lothar.katas.tennis.score.NormalScore.FOURTY;
import static com.github.lothar.katas.tennis.score.NormalScore.THIRTY;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Test;

public class ScoreBoardTest {

    private Players players = new Players("John", "Bob");
    private Player john = players.get("John");
    private Player bob = players.get("Bob");
    private ScorePrinter scoreBoard = new ScorePrinter(players, ONE_SET, Optional.empty());

    @Test
    public void should_board_display_scores_when_scores_are_blank() {
        assertThat(scoreBoard.toString()).isEqualTo("" + //
                "| Player | Set 1 | Score |\n" + //
                "| John   | 0     | 0     |\n" + //
                "| Bob    | 0     | 0     |\n");
    }

    @Test
    public void should_board_display_scores_when_player1_has_fifteen() {
        john.setScore(FIFTEEN);
        assertThat(scoreBoard.toString()).isEqualTo("" + //
                "| Player | Set 1 | Score |\n" + //
                "| John   | 0     | 15    |\n" + //
                "| Bob    | 0     | 0     |\n");
    }

    @Test
    public void should_board_display_scores_when_player1_has_thirty() {
        john.setScore(THIRTY);
        assertThat(scoreBoard.toString()).isEqualTo("" + //
                "| Player | Set 1 | Score |\n" + //
                "| John   | 0     | 30    |\n" + //
                "| Bob    | 0     | 0     |\n");
    }

    @Test
    public void should_board_display_scores_when_player1_has_fourty() {
        john.setScore(FOURTY);
        assertThat(scoreBoard.toString()).isEqualTo("" + //
                "| Player | Set 1 | Score |\n" + //
                "| John   | 0     | 40    |\n" + //
                "| Bob    | 0     | 0     |\n");
    }

    @Test
    public void should_board_display_scores_when_player1_has_advantage() {
        john.setScore(ADVANTAGE);
        assertThat(scoreBoard.toString()).isEqualTo("" + //
                "| Player | Set 1 | Score |\n" + //
                "| John   | 0     | ADV   |\n" + //
                "| Bob    | 0     | 0     |\n");
    }

    @Test
    public void should_board_display_scores_when_player2_has_fifteen() {
        bob.setScore(FIFTEEN);
        assertThat(scoreBoard.toString()).isEqualTo("" + //
                "| Player | Set 1 | Score |\n" + //
                "| John   | 0     | 0     |\n" + //
                "| Bob    | 0     | 15    |\n");
    }

    @Test
    public void should_board_display_scores_when_player2_has_thirty() {
        bob.setScore(THIRTY);
        assertThat(scoreBoard.toString()).isEqualTo("" + //
                "| Player | Set 1 | Score |\n" + //
                "| John   | 0     | 0     |\n" + //
                "| Bob    | 0     | 30    |\n");
    }

    @Test
    public void should_board_display_scores_when_player2_has_fourty() {
        bob.setScore(FOURTY);
        assertThat(scoreBoard.toString()).isEqualTo("" + //
                "| Player | Set 1 | Score |\n" + //
                "| John   | 0     | 0     |\n" + //
                "| Bob    | 0     | 40    |\n");
    }

    @Test
    public void should_board_display_scores_when_player2_has_advantage() {
        bob.setScore(ADVANTAGE);
        assertThat(scoreBoard.toString()).isEqualTo("" + //
                "| Player | Set 1 | Score |\n" + //
                "| John   | 0     | 0     |\n" + //
                "| Bob    | 0     | ADV   |\n");
    }

    @Test
    public void should_board_display_scores_when_player1_has_one_set() {
        john.setGamesWon(1);
        assertThat(scoreBoard.toString()).isEqualTo("" + //
                "| Player | Set 1 | Score |\n" + //
                "| John   | 1     | 0     |\n" + //
                "| Bob    | 0     | 0     |\n");
    }

    @Test
    public void should_board_display_scores_when_player2_has_one_set() {
        bob.setGamesWon(1);
        assertThat(scoreBoard.toString()).isEqualTo("" + //
                "| Player | Set 1 | Score |\n" + //
                "| John   | 0     | 0     |\n" + //
                "| Bob    | 1     | 0     |\n");
    }

    @Test
    public void should_board_display_scores_when_scores_are_blank_and_three_sets() {
        scoreBoard = new ScorePrinter(players, THREE_SETS, Optional.empty());
        assertThat(scoreBoard.toString()).isEqualTo("" + //
                "| Player | Set 1 | Set 2 | Set 3 | Score |\n" + //
                "| John   | 0     | 0     | 0     | 0     |\n" + //
                "| Bob    | 0     | 0     | 0     | 0     |\n");
    }

    @Test
    public void should_board_display_scores_when_scores_are_blank_and_five_sets() {
        scoreBoard = new ScorePrinter(players, FIVE_SETS, Optional.empty());
        assertThat(scoreBoard.toString()).isEqualTo("" + //
                "| Player | Set 1 | Set 2 | Set 3 | Set 4 | Set 5 | Score |\n" + //
                "| John   | 0     | 0     | 0     | 0     | 0     | 0     |\n" + //
                "| Bob    | 0     | 0     | 0     | 0     | 0     | 0     |\n");
    }

    @Test
    public void should_board_display_scores_when_scores_are_complex_and_five_sets() {
        scoreBoard = new ScorePrinter(players, FIVE_SETS, Optional.empty());
        john.setGamesWon(1);
        john.setupNewSet();
        john.setGamesWon(2);
        john.setupNewSet();
        john.setGamesWon(3);
        john.setupNewSet();
        john.setGamesWon(4);
        john.setupNewSet();
        john.setGamesWon(5);
        john.setupNewSet();
        john.setScore(FIFTEEN);
        bob.setGamesWon(6);
        bob.setupNewSet();
        bob.setGamesWon(7);
        bob.setupNewSet();
        bob.setGamesWon(8);
        bob.setupNewSet();
        bob.setGamesWon(9);
        bob.setupNewSet();
        bob.setGamesWon(10);
        bob.setupNewSet();
        bob.setScore(THIRTY);
        assertThat(scoreBoard.toString()).isEqualTo("" + //
                "| Player | Set 1 | Set 2 | Set 3 | Set 4 | Set 5 | Score |\n" + //
                "| John   | 1     | 2     | 3     | 4     | 5     | 15    |\n" + //
                "| Bob    | 6     | 7     | 8     | 9     | 10    | 30    |\n");
    }

    @Test
    public void should_board_display_winner_when_match_is_over() {
        scoreBoard = new ScorePrinter(players, ONE_SET, Optional.of(john));
        john.setGamesWon(6);
        assertThat(scoreBoard.toString()).isEqualTo("" + //
                "| Player | Set 1 | Result |\n" + //
                "| John   | 6     | WINNER |\n" + //
                "| Bob    | 0     |        |\n");
    }
}
