package com.github.lothar.katas.tennis;

import static com.github.lothar.katas.tennis.GameType.ONE_SET;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class ResultBoardTest {

    private Player player1 = new Player("John");
    private Player player2 = new Player("Bob");
    private ResultBoard resultBoard = new ResultBoard(asList(player1, player2), ONE_SET, player1);

    @Test
    public void should_board_display_winner_when_match_is_over() {
        player1.setGamesWon(6);
        assertThat(resultBoard.toString()).isEqualTo("" + //
                "| Player | Set 1 | Result |\n" + //
                "| John   | 6     | WINNER |\n" + //
                "| Bob    | 0     |        |\n");
    }
}
