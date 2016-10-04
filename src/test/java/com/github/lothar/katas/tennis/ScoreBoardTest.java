package com.github.lothar.katas.tennis;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class ScoreBoardTest {
    private Player player1 = new Player("John");
    private Player player2 = new Player("Bob");
    private ScoreBoard scoreBoard = new ScoreBoard(asList(player1, player2));

    @Test
    public void should_board_display_scores_when_scores_are_blank() {
        String string = scoreBoard.toString();
        assertThat(string).isEqualTo("" + //
                "| Player | Set | Score |\n" + //
                "| John   | 0   | 0     |\n" + //
                "| Bob    | 0   | 0     |\n");
    }
}
