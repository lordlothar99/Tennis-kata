package com.github.lothar.katas.tennis;

import static com.github.lothar.katas.tennis.GameType.FIVE_SETS;
import static com.github.lothar.katas.tennis.GameType.ONE_SET;
import static com.github.lothar.katas.tennis.GameType.THREE_SETS;
import static com.github.lothar.katas.tennis.NormalScore.ADVANTAGE;
import static com.github.lothar.katas.tennis.NormalScore.FIFTEEN;
import static com.github.lothar.katas.tennis.NormalScore.FOURTY;
import static com.github.lothar.katas.tennis.NormalScore.THIRTY;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Test;

public class ScoreBoardTest {

    private Players players = new Players("John", "Bob");
    private Player player1 = players.get("John");
    private Player player2 = players.get("Bob");
    private ScoreBoard scoreBoard = new ScoreBoard(players, ONE_SET, Optional.empty());

    @Test
    public void should_board_display_scores_when_scores_are_blank() {
        assertThat(scoreBoard.toString()).isEqualTo("" + //
                "| Player | Set 1 | Score |\n" + //
                "| John   | 0     | 0     |\n" + //
                "| Bob    | 0     | 0     |\n");
    }

    @Test
    public void should_board_display_scores_when_player1_has_fifteen() {
        player1.setScore(FIFTEEN);
        assertThat(scoreBoard.toString()).isEqualTo("" + //
                "| Player | Set 1 | Score |\n" + //
                "| John   | 0     | 15    |\n" + //
                "| Bob    | 0     | 0     |\n");
    }

    @Test
    public void should_board_display_scores_when_player1_has_thirty() {
        player1.setScore(THIRTY);
        assertThat(scoreBoard.toString()).isEqualTo("" + //
                "| Player | Set 1 | Score |\n" + //
                "| John   | 0     | 30    |\n" + //
                "| Bob    | 0     | 0     |\n");
    }

    @Test
    public void should_board_display_scores_when_player1_has_fourty() {
        player1.setScore(FOURTY);
        assertThat(scoreBoard.toString()).isEqualTo("" + //
                "| Player | Set 1 | Score |\n" + //
                "| John   | 0     | 40    |\n" + //
                "| Bob    | 0     | 0     |\n");
    }

    @Test
    public void should_board_display_scores_when_player1_has_advantage() {
        player1.setScore(ADVANTAGE);
        assertThat(scoreBoard.toString()).isEqualTo("" + //
                "| Player | Set 1 | Score |\n" + //
                "| John   | 0     | ADV   |\n" + //
                "| Bob    | 0     | 0     |\n");
    }

    @Test
    public void should_board_display_scores_when_player2_has_fifteen() {
        player2.setScore(FIFTEEN);
        assertThat(scoreBoard.toString()).isEqualTo("" + //
                "| Player | Set 1 | Score |\n" + //
                "| John   | 0     | 0     |\n" + //
                "| Bob    | 0     | 15    |\n");
    }

    @Test
    public void should_board_display_scores_when_player2_has_thirty() {
        player2.setScore(THIRTY);
        assertThat(scoreBoard.toString()).isEqualTo("" + //
                "| Player | Set 1 | Score |\n" + //
                "| John   | 0     | 0     |\n" + //
                "| Bob    | 0     | 30    |\n");
    }

    @Test
    public void should_board_display_scores_when_player2_has_fourty() {
        player2.setScore(FOURTY);
        assertThat(scoreBoard.toString()).isEqualTo("" + //
                "| Player | Set 1 | Score |\n" + //
                "| John   | 0     | 0     |\n" + //
                "| Bob    | 0     | 40    |\n");
    }

    @Test
    public void should_board_display_scores_when_player2_has_advantage() {
        player2.setScore(ADVANTAGE);
        assertThat(scoreBoard.toString()).isEqualTo("" + //
                "| Player | Set 1 | Score |\n" + //
                "| John   | 0     | 0     |\n" + //
                "| Bob    | 0     | ADV   |\n");
    }

    @Test
    public void should_board_display_scores_when_player1_has_one_set() {
        player1.setGamesWon(1);
        assertThat(scoreBoard.toString()).isEqualTo("" + //
                "| Player | Set 1 | Score |\n" + //
                "| John   | 1     | 0     |\n" + //
                "| Bob    | 0     | 0     |\n");
    }

    @Test
    public void should_board_display_scores_when_player2_has_one_set() {
        player2.setGamesWon(1);
        assertThat(scoreBoard.toString()).isEqualTo("" + //
                "| Player | Set 1 | Score |\n" + //
                "| John   | 0     | 0     |\n" + //
                "| Bob    | 1     | 0     |\n");
    }

    @Test
    public void should_board_display_scores_when_scores_are_blank_and_three_sets() {
        scoreBoard = new ScoreBoard(players, THREE_SETS, Optional.empty());
        assertThat(scoreBoard.toString()).isEqualTo("" + //
                "| Player | Set 1 | Set 2 | Set 3 | Score |\n" + //
                "| John   | 0     | 0     | 0     | 0     |\n" + //
                "| Bob    | 0     | 0     | 0     | 0     |\n");
    }

    @Test
    public void should_board_display_scores_when_scores_are_blank_and_five_sets() {
        scoreBoard = new ScoreBoard(players, FIVE_SETS, Optional.empty());
        assertThat(scoreBoard.toString()).isEqualTo("" + //
                "| Player | Set 1 | Set 2 | Set 3 | Set 4 | Set 5 | Score |\n" + //
                "| John   | 0     | 0     | 0     | 0     | 0     | 0     |\n" + //
                "| Bob    | 0     | 0     | 0     | 0     | 0     | 0     |\n");
    }

    @Test
    public void should_board_display_scores_when_scores_are_complex_and_five_sets() {
        scoreBoard = new ScoreBoard(players, FIVE_SETS, Optional.empty());
        player1.setGamesWon(1);
        player1.setupNewSet();
        player1.setGamesWon(2);
        player1.setupNewSet();
        player1.setGamesWon(3);
        player1.setupNewSet();
        player1.setGamesWon(4);
        player1.setupNewSet();
        player1.setGamesWon(5);
        player1.setupNewSet();
        player1.setScore(FIFTEEN);
        player2.setGamesWon(6);
        player2.setupNewSet();
        player2.setGamesWon(7);
        player2.setupNewSet();
        player2.setGamesWon(8);
        player2.setupNewSet();
        player2.setGamesWon(9);
        player2.setupNewSet();
        player2.setGamesWon(10);
        player2.setupNewSet();
        player2.setScore(THIRTY);
        assertThat(scoreBoard.toString()).isEqualTo("" + //
                "| Player | Set 1 | Set 2 | Set 3 | Set 4 | Set 5 | Score |\n" + //
                "| John   | 1     | 2     | 3     | 4     | 5     | 15    |\n" + //
                "| Bob    | 6     | 7     | 8     | 9     | 10    | 30    |\n");
    }

    @Test
    public void should_board_display_winner_when_match_is_over() {
        scoreBoard = new ScoreBoard(players, ONE_SET, Optional.of(player1));
        player1.setGamesWon(6);
        assertThat(scoreBoard.toString()).isEqualTo("" + //
                "| Player | Set 1 | Result |\n" + //
                "| John   | 6     | WINNER |\n" + //
                "| Bob    | 0     |        |\n");
    }
}
