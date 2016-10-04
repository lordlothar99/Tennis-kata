package com.github.lothar.katas.tennis;

import static com.github.lothar.katas.tennis.GameType.FIVE_SETS;
import static com.github.lothar.katas.tennis.GameType.ONE_SET;
import static com.github.lothar.katas.tennis.GameType.THREE_SETS;
import static com.github.lothar.katas.tennis.Score.ADVANTAGE;
import static com.github.lothar.katas.tennis.Score.FIFTEEN;
import static com.github.lothar.katas.tennis.Score.FOURTY;
import static com.github.lothar.katas.tennis.Score.THIRTY;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class ResultBoardTest {

    private Player player1 = new Player("John");
    private Player player2 = new Player("Bob");
    private ScoreBoard scoreBoard = new ScoreBoard(asList(player1, player2), ONE_SET);

    @Test
    public void should_board_display_winner_when_match_is_over() {
        scoreBoard = new ResultBoard(asList(player1, player2), ONE_SET, player1);
        player1.setGamesWon(6);
        assertThat(scoreBoard.toString()).isEqualTo("" + //
                "| Player | Set 1 | Result |\n" + //
                "| John   | 6     | WINNER |\n" + //
                "| Bob    | 0     |        |\n");
    }
}
