package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HighScoresTest {

    private HighScores testHighScores;

    @BeforeEach
    public void runBefore() {
        testHighScores = new HighScores();
    }

    @Test
    public void testGetTopHighScores() {
        testHighScores.addScore(5, "1");
        testHighScores.addScore(3, "2");
        testHighScores.addScore(7, "3");
        testHighScores.addScore(2, "4");
        testHighScores.addScore(10, "6");
        testHighScores.addScore(1, "5");

        assertEquals("10 6",testHighScores.getTopHighScores().get(0));
    }
}
