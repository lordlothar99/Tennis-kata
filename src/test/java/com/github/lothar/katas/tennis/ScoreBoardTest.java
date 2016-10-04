package com.github.lothar.katas.tennis;

import static com.github.lothar.katas.tennis.GameType.ONE_SET;
import static com.github.lothar.katas.tennis.Score.ADVANTAGE;
import static com.github.lothar.katas.tennis.Score.FIFTEEN;
import static com.github.lothar.katas.tennis.Score.FOURTY;
import static com.github.lothar.katas.tennis.Score.THIRTY;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class ScoreBoardTest {
    private Player player1 = new Player("John");
    private Player player2 = new Player("Bob");
    private ScoreBoard scoreBoard = new ScoreBoard(asList(player1, player2), ONE_SET);

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
}
